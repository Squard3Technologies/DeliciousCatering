
$(document).ready(function(){
    loadBookings();
});



function loadBookings() {
    const params = {
        transactionType: "getAdminBookings"
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
                //let table = document.getElementById("tblBookings");
                var table = document.getElementById('tblBookings').getElementsByTagName('tbody')[0];

                let counter = 1;
                $.each(bookings, function (i, item) {
                    console.log(item);
//                    let disablepayment = "";
//                    if(item.stageTypeId === 6 || item.stageTypeId === 7){
//                        disablepayment = 'disabled="disabled"';
//                    }
//                    
//                    let oldbalance = parseInt(item.finalQuoteAmount);
//                    let newbalance = parseInt(item.currentBalance);
//                    
//                    if(oldbalance <= newbalance){
//                        disablepayment = 'disabled="disabled"';
//                    }
                    
                    let newRow = table.insertRow(table.rows.length);
                    newRow.insertCell(0).innerHTML = item.id;
                    newRow.insertCell(1).innerHTML = item.client.name + " "+item.client.surname;
                    newRow.insertCell(2).innerHTML = item.cellMobile;
                    newRow.insertCell(3).innerHTML = item.typeOfEventDescription;
                    newRow.insertCell(4).innerHTML = item.eventDate;
                    newRow.insertCell(5).innerHTML = item.eventTime;
                    newRow.insertCell(6).innerHTML = item.quoteAmount;
                    newRow.insertCell(7).innerHTML = item.discountPercent;
                    newRow.insertCell(8).innerHTML = item.finalQuoteAmount;
                    newRow.insertCell(9).innerHTML = item.currentBalance;
                    newRow.insertCell(10).innerHTML = item.stageDescription;
                    newRow.insertCell(11).innerHTML =
                            '<button type="button" class="btn btn-sm btn-danger" onclick="cancelBooking(this)" data-bid="' + item.id + '" title="Decline the booking"><i class="fa-solid fa-calendar-xmark"></i></button> &nbsp;&nbsp;' +
                            '<button type="button" class="btn btn-sm btn-primary" onclick="PaymentView(this)" data-bid="' + item.id + '"  title="Review the booking"><i class="fa-solid fa-pen-to-square"></i></button> &nbsp;&nbsp;' +
                            '<button type="button" class="btn btn-sm btn-success" onclick="paymentView(this)" data-bid="' + item.id + '" title="Accept the booking"><i class="fa-solid fa-hand-holding-dollar"></i></button>';
counter += 1;
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
