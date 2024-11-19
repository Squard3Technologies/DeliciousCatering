
$(document).ready(function () {

    $("#loginForm").on('submit', function () {
        debugger;
        const frm = $(this);
        let url = frm.attr("action");
        const params = frm.serialize();

        Swal.fire({
            title: 'PLEASE WAIT',
            html: 'Authenticating',
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
                    if (data.data.Role === "Client") {
                        location.href = "/DeliciousCatering/clientdashboard.html";
                    } 
                    else if (data.data.Role === "Admin") {
                        location.href = "/DeliciousCatering/dashboard.html";
                    }
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


