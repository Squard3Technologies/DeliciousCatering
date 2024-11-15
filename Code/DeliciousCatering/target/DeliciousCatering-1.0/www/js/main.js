$(document).ready(function () {

    $("#loginForm").on("submit", function () {

        Swal.fire({
            title: 'PLEASE WAIT',
            html: 'Saving Client Service Form',
            allowOutsideClick: false,
            onBeforeOpen: () => {
                Swal.showLoading();
            }
        });

    });

});