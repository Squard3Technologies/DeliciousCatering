
let bookings = Array();
$(document).ready(function () {

    $("#frmViewBookings").on('submit', function () {
        debugger;
        const frm = $(this);
        let url = frm.attr("action");
        const params = frm.serialize();
        Swal.fire({
            title: 'PLEASE WAIT',
            html: 'Getting data..',
            allowOutsideClick: false,
            onBeforeOpen: () => {
                Swal.showLoading();
            }
        });

        $.ajax({
            url: url,
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
                    var html = "";
                    html += '<table class="table table-striped table-hover w-100">';
                    html += '<thead class="table-primary">';
                    html += '<tr>';
                    html += '<th scope="col">Booking Number</th>';
                    html += '<th scope="col">Event Type</th>';
                    html += '<th scope="col">Event Date</th>';
                    html += '<th scope="col">Event Time</th>';
                    html += '<th scope="col">Total Cost</th>';
                    html += '<th scope="col">Balance</th>';
                    html += '<th scope="col">Status</th>';
                    html += '<th scope="col">Actions</th>';
                    html += '</tr>';
                    html += '</thead>';
                    html += '</tbody>';
                    $.each(bookings, function (i, item) {
                        
                        console.log(item);
                        
                        html += '<tr>';
                        
                        html += '<td>' + item.id + '</td>';
                        html += '<td>' + item.typeofEvent + '</td>';
                        html += '<td>' + item.eventDate + '</td>';
                        html += '<td>' + item.eventTime + '</td>';
                        html += '<td>R ' + item.finalQuoteAmount + '</td>';
                        html += '<td>R ' + item.currentBalance + '</td>';
                        html += '<td>' +  + '</td>';
                        
                        html += '</tr>';

                    });
                    html += '</tbody>';
                    html += '</table>';
                    $('#tblBookings').html(html);
                    
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
        event.preventDefault();
    });

    $("#frmViewBookings").submit();
});

