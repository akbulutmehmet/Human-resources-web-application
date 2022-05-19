$(document).on('click','.btnPersonelSil',function () {
    $(".btnPersonelSil").click(function () {
        let data_id = $(this).data("id");
        let url = "personelSil";
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
                        personelId : data_id
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
                            window.location.href = "personelListele";
                        }
                    },
                    dataType: "json"
                });
            } else if (result.isDenied) {
                Swal.fire('Silme işlemi iptal edildi.', '', 'success')
            }
        })


    });
});
$("#personelTables").DataTable(
    {
        serverSide : true,
        processing: true,
        ajax : {
            url : 'personelData',
            method: 'POST',
            dataType:'JSON'
        },
        columns : [
            {data : 'personelId'},
            {data : 'personelAd'},
            {data : 'personelSoyad'},
            {data : 'personelTc'},
            {data : 'personelCinsiyet'},
            {data : 'isBaslangicTarihi'},
            {data : 'departman'},
            {data : 'gorev'},
            {data : 'islemler',orderable:false},
        ],
        lengthMenu: [
            [5,10,15,20,25,50,100,1000,3000,5000,10000,-1],
            [5,10,15,20,25,50,100,1000,3000,5000,10000,'All']
        ],
        language: {
            url: "//cdn.datatables.net/plug-ins/1.12.0/i18n/tr.json",
        }
    }
);
$("#dataTables").DataTable(
    {
        language: {
            url: "//cdn.datatables.net/plug-ins/1.12.0/i18n/tr.json",
        }
    }
);
$("#btnIkKaydet").click(function () {
    let url = "insanKaynaklariKaydet";
    let isim = $("#isim").val().trim();
    let soyisim = $("#soyisim").val();
    let email = $("#email").val();
    let password = $("#password").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            isim : isim,
            soyisim : soyisim,
            email : email,
            password : password
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

                window.location.href = "insanKaynaklariListele";

            }
        },
        dataType: "json"
    });
});
$("#btnIkGuncelle").click(function () {
    let url = $("#dataUrl").val();
    let redirect_url = $("#redirectUrl").val();
    let data_id = $("#ikId").val();
    let isim = $("#isim").val();
    let soyisim = $("#soyisim").val();
    let email = $("#email").val();
    let password = $("#password").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            id : data_id,
            isim : isim,
            soyisim : soyisim,
            email : email,
            password : password
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: false,
                timer: 3000
            });
            setTimeout(function () {

            },3000);
            if(response.exist) {
                window.location.href = redirect_url;
            }
        },
        dataType: "json"
    });

});
$(".btnIkSil").click(function () {
        let data_id = $(this).data("id");
        let url = "insanKaynaklariSil";
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
                        id : data_id
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
                            window.location.href = "insanKaynaklariListele";
                        }
                    },
                    dataType: "json"
                });
            } else if (result.isDenied) {
                Swal.fire('Silme işlemi iptal edildi.', '', 'success')
            }
        })

    });
$("#btnDepartmanKaydet").click(function () {
    let url = "departmanKaydet";
    let departmanAdi = $("#departmanAdi").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            departmanAdi : departmanAdi,
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: false,
                timer: 10000
            });
            setTimeout(function () {

            },3000);
            if(response.exist) {

                window.location.href = "departmanListele";

            }
        },
        dataType: "json"
    });

});
$("#btnDepartmanGuncelle").click(function () {
    let url = $("#dataUrl").val();
    let redirect_url = $("#redirectUrl").val();
    let departmanId = $("#departmanId").val();
    let departmanAdi = $("#departmanAdi").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            departmanId : departmanId,
            departmanAdi : departmanAdi
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: false,
                timer: 3000
            });
            setTimeout(function () {

            },3000);
            if(response.exist) {
                window.location.href = redirect_url;
            }
        },
        dataType: "json"
    });
});
$(".btnDepartmanSil").click(function () {
        let data_id = $(this).data("id");
        let url = "departmanSil";
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
                        id : data_id
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
                            window.location.href = "departmanListele";
                        }
                    },
                    dataType: "json"
                });
            } else if (result.isDenied) {
                Swal.fire('Silme işlemi iptal edildi.', '', 'success')
            }
        })

    });
$("#btnGorevKaydet").click(function () {
    let url = "gorevKaydet";
    let departmanId = $("#departmanId").val();
    let gorevAdi = $("#gorevAdi").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            departmanId : departmanId,
            gorevAdi : gorevAdi
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: false,
                timer: 10000
            });
            setTimeout(function () {

            },3000);
            if(response.exist) {

                window.location.href = "gorevListele";

            }
        },
        dataType: "json"
    });


});
$("#btnGorevUpdate").click(function () {
    let url = $("#dataUrl").val();
    let redirect_url = $("#redirectUrl").val();
    let departmanId = $("#departmanId").val();
    let gorevAdi = $("#gorevAdi").val();
    let gorevId = $("#gorevId").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            departmanId : departmanId,
            gorevAdi : gorevAdi,
            gorevId : gorevId
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: false,
                timer: 3000
            });
            setTimeout(function () {

            },3000);
            if(response.exist) {
                window.location.href = redirect_url;
            }
        },
        dataType: "json"
    });
});
$(".btnGorevSil").click(function () {
    let data_id = $(this).data("id");
    let url = "gorevSil";
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
                    id : data_id
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
                        window.location.href = "gorevListele";
                    }
                },
                dataType: "json"
            });
        } else if (result.isDenied) {
            Swal.fire('Silme işlemi iptal edildi.', '', 'success')
        }
    })


});
$("#btnPersonelKaydet").click(function () {
    let url = "personelKaydet";
    let personelAd = $("#personelAd").val();
    let personelSoyad = $("#personelSoyad").val();
    let personelTc = $("#personelTc").val();
    let personelCinsiyet = $("#personelCinsiyet").val();
    let personelMaas = $("#personelMaas").val();
    let personelGorevId = $("#personelGorevId").val();
    let isBaslangicTarihi = $("#isBaslangicTarihi").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            personelAd : personelAd,
            personelSoyad : personelSoyad,
            personelTc : personelTc,
            personelCinsiyet : personelCinsiyet,
            personelMaas : personelMaas,
            isBaslangicTarihi : isBaslangicTarihi,
            personelGorevId : personelGorevId
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: false,
                timer: 10000
            });
            setTimeout(function () {

            },3000);
            if(response.exist) {

                window.location.href = "personelListele";

            }
        },
        dataType: "json"
    });





});
$("#btnPersonelGuncelle").click(function () {
    let url = $("#personelUpdateUrl").val();
    let redirectUrl = $("#redirectUrl").val();
    let personelId = $("#personelId").val();
    let personelAd = $("#personelAd").val();
    let personelSoyad = $("#personelSoyad").val();
    let personelTc = $("#personelTc").val();
    let personelCinsiyet = $("#personelCinsiyet").val();
    let personelMaas = $("#personelMaas").val();
    let personelGorevId = $("#personelGorevId").val();
    let isBaslangicTarihi = $("#isBaslangicTarihi").val();

    $.ajax({
        type: "POST",
        url: url,
        data: {
            personelId : personelId,
            personelAd : personelAd,
            personelSoyad : personelSoyad,
            personelTc : personelTc,
            personelCinsiyet : personelCinsiyet,
            personelMaas : personelMaas,
            isBaslangicTarihi : isBaslangicTarihi,
            personelGorevId : personelGorevId
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: false,
                timer: 10000
            });
            setTimeout(function () {

            },3000);
            if(response.exist) {

                window.location.href = redirectUrl  ;

            }
        },
        dataType: "json"
    });





});


$("#btnIzinliPersonelEkle").click(function () {
    let url = "izinliPersonelKaydet";
    let personelId = $("#personelId").val();
    let izinBaslangicTarihi = $("#izinBaslangicTarihi").val();
    let izinBitisTarihi = $("#izinBitisTarihi").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            personelId : personelId,
            izinBaslangicTarihi : izinBaslangicTarihi,
            izinBitisTarihi : izinBitisTarihi,

        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: false,
                timer: 10000
            });
            setTimeout(function () {

            },3000);
            if(response.exist) {

                window.location.href = "izinliPersonelListele";

            }
        },
        dataType: "json"
    });
});
$(".btnIzinliPersonelSil").click(function () {
    let data_id = $(this).data("id");
    let url = "izinliPersonelSil";
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
                    izinliPersonelId : data_id
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
                        window.location.href = "izinliPersonelListele";
                    }
                },
                dataType: "json"
            });
        } else if (result.isDenied) {
            Swal.fire('Silme işlemi iptal edildi.', '', 'success')
        }
    })


});
$("#btnIzinliPersonelGuncelle").click(function () {

    let url = $("#dataUrl").val();
    let redirectUrl = $("#redirectUrl").val();
    let personelId = $("#personelId").val();
    let izinliPersonelId = $("#izinliPersonelId").val();
    let izinBaslangicTarihi = $("#izinBaslangicTarihi").val();
    let izinBitisTarihi = $("#izinBitisTarihi").val();
    $.ajax({
        type: "POST",
        url: url,
        data: {
            personelId : personelId,
            izinBaslangicTarihi : izinBaslangicTarihi,
            izinBitisTarihi : izinBitisTarihi,
            izinliPersonelId :izinliPersonelId
        },
        success: function (response) {
            Swal.fire({
                position: 'center',
                icon: response.icon,
                title: response.title,
                showConfirmButton: false,
                timer: 10000
            });
            setTimeout(function () {

            },3000);
            if(response.exist) {

                window.location.href = redirectUrl  ;

            }
        },
        dataType: "json"
    });


});
function gorevGetir (val) {
    let url = $("#personelDepartman").data("url");
    $("#bosDepartman").remove();
    $("#personelId").empty();
    url = url + "/gorevGetir";
    $.ajax({
        type: "POST",
        url: url,
        data: {
            departmanId : val
        },
        success: function (response) {
            $("#personelGorevId").empty();
            $("#personelGorevId").html(response.gorevler);
        },
        dataType: "json"
    });
}
function personelGetir (val) {
    let url = $("#personelGorevId").data("url");
    $("#bosPersonel").remove();
    url = url + "/personelGetir";
    $.ajax({
        type: "POST",
        url: url,
        data: {
            gorevId : val
        },
        success: function (response) {
            $("#personelId").empty();
            $("#personelId").html(response.personeller);
        },
        dataType: "json"
    });
}
function tcKontrol(val) {
    let url = $("#personelTc").data("url");
    $.ajax({
        type: "POST",
        url: url,
        data: {
            personelTc : val
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