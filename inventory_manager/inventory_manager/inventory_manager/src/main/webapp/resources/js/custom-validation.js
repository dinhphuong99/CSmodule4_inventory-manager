
$(function () {
    $("#frmCreateCustomer").validate({
        onkeyup: function(element) { $(element).valid() },
        onclick: false,
        onfocusout: false,
        rules: {
            fullName: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            email: {
                required: true,
                email: true,
                minlength: 10,
                maxlength: 50
            },
            address: {
                required: false,
                minlength: 5,
                maxlength: 50
            }
        },
        errorLabelContainer: "#modalCreateCustomer .modal-alert-danger",
        errorPlacement: function (error, element) {
            error.appendTo("#modalCreateCustomer .modal-alert-danger");
        },
        showErrors: function(errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalCreateCustomer .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalCreateCustomer .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalCreateCustomer input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        messages: {
            fullName: {
                required: "Vui lòng nhập tên đầy đủ",
                minlength: jQuery.validator.format("Họ tên tối thiểu {0} ký tự"),
                maxlength: jQuery.validator.format("Họ tên tối đa {0} ký tự")
            },
            email: {
                required: "Vui lòng nhập email đầy đủ",
                email: "Vui lòng nhập đúng định dạng email",
                minlength: jQuery.validator.format("Email tối thiểu {0} ký tự"),
                maxlength: jQuery.validator.format("Email tối đa {0} ký tự")
            },
            address: {
                minlength: jQuery.validator.format("Địa chỉ tối thiểu {0} ký tự"),
                maxlength: jQuery.validator.format("Địa chỉ tối đa {0} ký tự")
            }
        }
    });

    $("#frmUpdateCustomer").validate({
        onkeyup: function(element) {$(element).valid()},
        onclick: false,
        rules: {
            upFullName: {
                required: true,
                minlength: 5,
                maxlength: 50
            }
        },
        errorLabelContainer: "#modalUpdateCustomer .modal-alert-danger",
        errorPlacement: function (error, element) {
            error.appendTo("#modalUpdateCustomer .modal-alert-danger");
        },
        showErrors: function(errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalUpdateCustomer .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalUpdateCustomer .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalUpdateCustomer input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        messages: {
            upFullName: {
                required: "Bắt buộc nhập tên đầy đủ",
                minlength: "Hãy nhập tối thiểu 5 ký tự",
                maxlength: "Hãy nhập tối đa 50 ký tự"
            }
        }
    });

    $("#frmWithdraw").validate({
        onkeyup: function(element) {$(element).valid()},
        onclick: false,
        onfocusout: false,
        rules: {
            transactionAmount: {
                required: true,
                maxlength: 14
            }
        },
        errorLabelContainer: "#modalWithdraw .alert-danger",
        errorPlacement: function (error, element) {
            error.appendTo("#modalWithdraw .alert-danger");
        },
        showErrors: function(errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#modalWithdraw .modal-alert-danger").removeClass("hide").addClass("show");
            } else {
                $("#modalWithdraw .modal-alert-danger").removeClass("show").addClass("hide").empty();
                $("#modalWithdraw input.error").removeClass("error");
            }
            this.defaultShowErrors();
        },
        messages: {
            transactionAmount: {
                required: "Không được để trống trường này",
                maxlength: "Số tiền tối đa là 10.000.000"
            }
        }
    });
});