function adayTcKontrol(val) {
    let url = $("#adayTc").data("url");
    $.ajax({
        type: "POST",
        url: url,
        data: {
            adayTc : val
        },
        success: function (response) {
            if(!response.exist) {
                Swal.fire({
                    position: 'center',
                    icon: response.icon,
                    title: response.title,
                    showConfirmButton: false,
                    timer: 10000
                });

            }
            else {
                Swal.fire({
                    position: 'center',
                    icon: response.icon,
                    title: response.title,
                    showConfirmButton: false,
                    timer: 10000
                });

            }
        },
        dataType: "json"
    });
}