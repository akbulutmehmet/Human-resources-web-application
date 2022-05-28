$(function () {
    var dtToday = new Date();

    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();

    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();

    var maxDate = year + '-' + month + '-' + day;


    $("#isBaslangicTarihi").attr("max",maxDate);
    $("#isBitisTarihi").attr("max",maxDate);
    $("#adayDogumTarihi").attr("max",maxDate);
});
$("#dataTables").DataTable(
    {
        language: {
            url: "//cdn.datatables.net/plug-ins/1.12.0/i18n/tr.json",
        }
    }
);

const base_url = "http://localhost:9091/bitirmeprojesi";
$(".btnAdayEgitimSil").click(function () {
    let data_id = $(this).data("id");
    let url = base_url + "/aday/adayEgitimSil";
    Swal.fire({
        title: 'Silmek İstediğinize Emin Misiniz?',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Sil',
        denyButtonText: `İptal`,
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            $.ajax({
                type: "POST",
                url: url,
                data: {
                    adayEgitimId : data_id
                },
                success: function (response) {
                    Swal.fire({
                        position: 'center',
                        icon: response.icon,
                        title: response.title,
                        showConfirmButton: false,
                        timer: 10000
                    });

                    if(response.exist) {
                        window.location.href = base_url + "/aday/adayEgitimListele";
                    }
                },
                dataType: "json"
            });
        } else if (result.isDenied) {
            Swal.fire('Silme işlemi iptal edildi.', '', 'success')
        }
    })

});

$("#btnAdayGuncelle").click(function () {
    let url = base_url+ "/aday/adayGuncelle";
    let adayId = $(this).data("id");
    let adayAd = $("#adayAd").val();
    let adaySoyad = $("#adaySoyad").val();
    let adayEposta = $("#adayEposta").val();
    let adaySifre = $("#adaySifre").val();
    let adayCinsiyet = $("#adayCinsiyet").val();
    let adayDogumTarihi = $("#adayDogumTarihi").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            adayId:adayId,
            adayAd : adayAd,
            adaySoyad : adaySoyad,
            adayEposta : adayEposta,
            adaySifre : adaySifre,
            adayCinsiyet : adayCinsiyet,
            adayDogumTarihi:adayDogumTarihi
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: true,
                timer: 3000
            });
            setTimeout(function () {
                if(response.exist) {

                    window.location.href = base_url + "/aday/";

                }
            },1000)
        },
        dataType: "json"
    });


});
$("#btnAdayIsTecrubesiEkle").click(function () {
    let url = base_url + "/aday/adayIsTecrubesiKaydet";
    let isyeriAdi = $("#isyeriAdi").val();
    let gorevAdi = $("#gorevAdi").val();
    let isBaslangicTarihi = $("#isBaslangicTarihi").val();
    let isBitisTarihi = $("#isBitisTarihi").val();
    let gorevliAdi = $("#gorevliAdi").val();
    let gorevliUnvan = $("#gorevliUnvan").val();
    let gorevliTelefon = $("#gorevliTelefon").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            isyeriAdi:isyeriAdi,
            gorevAdi : gorevAdi,
            isBaslangicTarihi : isBaslangicTarihi,
            isBitisTarihi : isBitisTarihi,
            gorevliAdi : gorevliAdi,
            gorevliUnvan : gorevliUnvan,
            gorevliTelefon:gorevliTelefon
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: true,
                timer: 3000
            });
            setTimeout(function () {
                if(response.exist) {

                    window.location.href = base_url + "/aday/adayIsTecrubeListele";

                }
            },1000)
        },
        error : function () {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: true,
                timer: 3000
            });
        },
        dataType: "json"
    });

});
$("#btnAdayIsTecrubesiGuncelle").click(function () {
    let url = base_url + "/aday/adayIsTecrubesiKaydet";
    let adayIsTecrubeId = $(this).data("id");
    let isyeriAdi = $("#isyeriAdi").val();
    let gorevAdi = $("#gorevAdi").val();
    let isBaslangicTarihi = $("#isBaslangicTarihi").val();
    let isBitisTarihi = $("#isBitisTarihi").val();
    let gorevliAdi = $("#gorevliAdi").val();
    let gorevliUnvan = $("#gorevliUnvan").val();
    let gorevliTelefon = $("#gorevliTelefon").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            adayIsTecrubeId:adayIsTecrubeId,
            isyeriAdi:isyeriAdi,
            gorevAdi : gorevAdi,
            isBaslangicTarihi : isBaslangicTarihi,
            isBitisTarihi : isBitisTarihi,
            gorevliAdi : gorevliAdi,
            gorevliUnvan : gorevliUnvan,
            gorevliTelefon:gorevliTelefon
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: true,
                timer: 3000
            });
            setTimeout(function () {
                if(response.exist) {

                    window.location.href = base_url + "/aday/adayIsTecrubeListele";

                }
            },1000)
        },
        error : function () {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: true,
                timer: 3000
            });
        },
        dataType: "json"
    });

});

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
$(".btnAdayIsTecrubeSil").click(function () {
    let data_id = $(this).data("id");
    let url = base_url + "/aday/adayIsTecrubesiSil";
    Swal.fire({
        title: 'Silmek İstediğinize Emin Misiniz?',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Sil',
        denyButtonText: `İptal`,
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            $.ajax({
                type: "POST",
                url: url,
                data: {
                    adayIsTecrubeId : data_id
                },
                success: function (response) {
                    Swal.fire({
                        position: 'center',
                        icon: response.icon,
                        title: response.title,
                        showConfirmButton: false,
                        timer: 10000
                    });

                    if(response.exist) {
                        window.location.href = base_url + "/aday/adayIsTecrubeListele";
                    }
                },
                dataType: "json"
            });
        } else if (result.isDenied) {
            Swal.fire('Silme işlemi iptal edildi.', '', 'success')
        }
    })

});
function maxLengthCheck(object)
{
    if (object.value.length > object.maxLength)
        object.value = object.value.slice(0, object.maxLength)
}