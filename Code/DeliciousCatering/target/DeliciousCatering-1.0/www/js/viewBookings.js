
let bookings = Array();
$(document).ready(function () {
    loadBookings();
    $("#frmMakePayment").on('submit', function () {
        debugger;
        const frm = $(this);
        let url = frm.attr("action");
        const params = frm.serialize();
        Swal.fire({
            title: 'PLEASE WAIT',
            html: 'Processing payment..',
            allowOutsideClick: false,
            onBeforeOpen: () => {
                Swal.showLoading();
            }
        });

        $.ajax({
            url: url,
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            data: params,
            dataType: "json",
            async: true,
            cache: false,
            success: function (data) {
                debugger;
                console.log(data);
                if (data.status) {
                    Swal.close();
                    debugger;
                    $('#paymentModal').modal('toggle');
                    location.reload();
                } else {
                    Swal.close();
                    Swal.fire({
                        title: 'ERROR',
                        html: data.message,
                        type: 'error',
                        width: 500,
                        showCancelButton: false,
                        confirmButtonText: 'Dismiss',
                        allowOutsideClick: false,
                        reverseButtons: true
                    }).then((result) => {

                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                debugger;
                Swal.close();
                Swal.fire({
                    title: 'ERROR',
                    html: XMLHttpRequest.responseText,
                    type: 'error',
                    width: 1100,
                    showCancelButton: false,
                    confirmButtonText: 'Dismiss',
                    allowOutsideClick: false,
                    reverseButtons: true
                }).then((result) => {

                });
            }
        });
        event.preventDefault();
    });
});


function cancelBooking(button){
    let bookingId = $(button).attr("data-bid");
    Swal.fire({
    title: 'ARE YOU SURE?',
    text: "You want to cancel the booking. You will then need to contact the office to facilitate for a refund.",
    type: 'question',
    width: 500,
    showCancelButton: true,
    cancelButtonColor: '#6c757d',
    confirmButtonColor: '#004C8E',
    cancelButtonText: 'No',
    confirmButtonText: 'Yes',
    allowOutsideClick: false,
    reverseButtons: true
}).then((result) => {
    Swal.close();
    if (result.value) {
        cancelBookingAsync(bookingId);
    }
});
}


function paymentView(button) {
    debugger;
    let bookingId = $(button).attr("data-bid");
    showPaymentModal(bookingId);
}


function showPaymentModal(bookingId) {
    debugger;
    $('#paymentModal').on('shown.bs.modal', function () {
        debugger;
        $(".modal-body #bookingId").val(bookingId);
    });
    $('#paymentModal').modal('show');
}


function cancelBookingAsync(bookingId){
    const params = {
        transactionType: "cancelBooking",
        txtBookingId: bookingId
    };
    $.ajax({
        url: "/DeliciousCatering/manageBookings",
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        data: params,
        dataType: "json",
        async: true,
        cache: false,
        success: function (data) {
            debugger;
            console.log(data);
            if (data.status) {
                Swal.close();
                debugger;
                location.reload();
            } else {
                Swal.close();
                Swal.fire({
                    title: 'ERROR',
                    html: data.message,
                    type: 'error',
                    width: 500,
                    showCancelButton: false,
                    confirmButtonText: 'Dismiss',
                    allowOutsideClick: false,
                    reverseButtons: true
                }).then((result) => {

                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            debugger;
            Swal.close();
            Swal.fire({
                title: 'ERROR',
                html: XMLHttpRequest.responseText,
                type: 'error',
                width: 1100,
                showCancelButton: false,
                confirmButtonText: 'Dismiss',
                allowOutsideClick: false,
                reverseButtons: true
            }).then((result) => {

            });
        }
    });
}




function loadBookings() {
    const params = {
        transactionType: "getClientBookings"
    };
    Swal.fire({
        title: 'PLEASE WAIT',
        html: 'Getting data..',
        allowOutsideClick: false,
        onBeforeOpen: () => {
            Swal.showLoading();
        }
    });

    $.ajax({
        url: "/DeliciousCatering/manageBookings",
        type: "GET",
        contentType: "application/x-www-form-urlencoded",
        data: params,
        dataType: "json",
        async: true,
        cache: false,
        success: function (data) {
            debugger;
            console.log(data);
            if (data.status) {
                Swal.close();
                debugger;
                bookings = data.data.booking;
                let table = document.getElementById("tblBookings");
                $.each(bookings, function (i, item) {
                    console.log(item);
                    let disablepayment = "";
                    if(item.stageTypeId === 6 || item.stageTypeId === 7){
                        disablepayment = 'disabled="disabled"';
                    }
                    
                    let oldbalance = parseInt(item.finalQuoteAmount);
                    let newbalance = parseInt(item.currentBalance);
                    
                    if(oldbalance <= newbalance){
                        disablepayment = 'disabled="disabled"';
                    }
                    
                    let newRow = table.insertRow(table.rows.length);
                    newRow.insertCell(0).innerHTML = item.id;
                    newRow.insertCell(1).innerHTML = item.typeOfEventDescription;
                    newRow.insertCell(2).innerHTML = item.eventDate;
                    newRow.insertCell(3).innerHTML = item.eventTime;
                    newRow.insertCell(4).innerHTML = item.quoteAmount;
                    newRow.insertCell(5).innerHTML = item.discountPercent;
                    newRow.insertCell(6).innerHTML = item.finalQuoteAmount;
                    newRow.insertCell(7).innerHTML = item.currentBalance;
                    newRow.insertCell(8).innerHTML = item.stageDescription;
                    newRow.insertCell(9).innerHTML =
                            '<button type="button" class="btn btn-sm btn-danger" onclick="cancelBooking(this)" data-bid="' + item.id + '" ><i class="fa-solid fa-calendar-xmark"></i></button> &nbsp;&nbsp;' +
                            //'<button type="button" class="btn btn-sm btn-primary" onclick="PaymentView(this)" data-bid="' + item.id + '" ><i class="fa-solid fa-pen-to-square"></i></button> &nbsp;&nbsp;' +
                            '<button type="button" class="btn btn-sm btn-success" onclick="paymentView(this)" data-bid="' + item.id + '" '+disablepayment+'><i class="fa-solid fa-hand-holding-dollar"></i></button>';

                });
            } else {
                Swal.close();
                Swal.fire({
                    title: 'SUCCESS',
                    html: data.message,
                    type: 'success',
                    width: 1100,
                    showCancelButton: false,
                    confirmButtonText: 'Dismiss',
                    allowOutsideClick: false,
                    reverseButtons: true
                }).then((result) => {

                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            debugger;
            Swal.close();
            Swal.fire({
                title: 'ERROR',
                html: XMLHttpRequest.responseText,
                type: 'error',
                width: 1100,
                showCancelButton: false,
                confirmButtonText: 'Dismiss',
                allowOutsideClick: false,
                reverseButtons: true
            }).then((result) => {

            });
        }
    });
}
