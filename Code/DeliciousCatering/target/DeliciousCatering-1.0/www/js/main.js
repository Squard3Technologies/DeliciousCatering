$(document).ready(function () {


    $("#eventDate").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'yy-mm-dd',
        constrainInput: true,
        minDate: new Date()
    });

    $("#txtDateOfBirth").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'yy-mm-dd',
        constrainInput: true,
        maxDate: new Date()
    });

    $('#eventTime').timepicker({
        'showDuration': true,
        'timeFormat': 'HH:mm'
    });

    $("#frmCreateAccount").on('submit', function () {
        debugger;
        const frm = $(this);
        let url = frm.attr("action");
        const params = frm.serialize();

        Swal.fire({
            title: 'PLEASE WAIT',
            html: 'Creating account',
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
                    Swal.fire({
                        title: 'SUCCESS',
                        html: data.message,
                        type: 'success',
                        width: 500,
                        showCancelButton: false,
                        confirmButtonText: 'Dismiss',
                        allowOutsideClick: false,
                        reverseButtons: true
                    }).then((result) => {
                        location.reload();
                    });
                } else {
                    Swal.close();
                    Swal.fire({
                        title: 'ERROR',
                        html: data.message,
                        type: 'error',
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




    $("#frmBookings").on('submit', function () {
        debugger;
        const frm = $(this);
        let url = frm.attr("action");
        const params = frm.serialize();

        Swal.fire({
            title: 'PLEASE WAIT',
            html: 'Creating account',
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
            //contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            cache: false,
            success: function (data) {
                debugger;
                console.log(data);
                if (data.status) {
                    Swal.close();
                    Swal.fire({
                        title: 'SUCCESS',
                        html: data.message,
                        type: 'success',
                        width: 500,
                        showCancelButton: false,
                        confirmButtonText: 'Dismiss',
                        allowOutsideClick: false,
                        reverseButtons: true
                    }).then((result) => {
                        frm.reset();
                        //location.reload();
                    });
                } else {
                    Swal.close();
                    Swal.fire({
                        title: 'ERROR',
                        html: data.message,
                        type: 'error',
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

    debugger;
    let frmId = $("#frmid").val();
    if (frmId !== "7D84B642-C367-401B-994C-E7418F7592C7") {
        getSession();
    }

});


function getSession() {
    Swal.fire({
        title: 'PLEASE WAIT',
        html: 'Loading......',
        allowOutsideClick: false,
        onBeforeOpen: () => {
            Swal.showLoading();
        }
    });
    $.ajax({
        url: "/DeliciousCatering/clientAuth",
        type: "GET",
        contentType: "application/x-www-form-urlencoded",
        //data: params,
        dataType: "json",
        async: true,
        cache: false,
        success: function (data) {
            debugger;
            console.log(data);
            if (data.status) {
                Swal.close();
                $("#authenticatedUser").html(data.data.name + " " + data.data.surname);
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
                    location.href = "/DeliciousCatering"

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