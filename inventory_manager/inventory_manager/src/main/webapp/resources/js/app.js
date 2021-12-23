class App {

    static DOMAIN = location.origin;
    static BASE_URL_CUSTOMER = this.DOMAIN + "/api/customers";
    static BASE_URL_TRANSFER = this.DOMAIN + "/api/transfers";
    static BASE_URL_CATEGORY = this.DOMAIN + "/api/categories";
    static BASE_URL_PRODUCT = this.DOMAIN + "/api/products";
    static BASE_URL_USER = this.DOMAIN + "/api/users";
    static BASE_URL_ORDER = this.DOMAIN + "/api/orders";

    static showSuspendedConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to suspend the selected customer ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, please suspend this client !',
            cancelButtonText: 'Cancel',
        })
    }

    static showDeleteConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to delete the selected ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, please delete this client !',
            cancelButtonText: 'Cancel',
        })
    }

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }

    static formatNumber() {
        $(".num-space").number(true, 0, ',', ' ');
        $(".num-point").number(true, 0, ',', '.');
        $(".num-comma").number(true, 0, ',', ',');
    }

    static formatNumberSpace(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }

    static removeFormatNumberSpace(x) {
        return x.toString().replace(" ", "");
    }

    static formatTooltip() {
        $('[data-toggle="tooltip"]').tooltip();
    }
}

class Customer {
    constructor(id, fullName, email, phone, address, balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }
}

class Sender {
    constructor(id, fullName, email, phone, address, balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }
}

class Recipient {
    constructor(id, fullName, email, phone, address, balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }
}

class Deposit {
    constructor(customerId, fullName, balance, transactionAmount) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.balance = balance;
        this.transactionAmount = transactionAmount;
    }
}

class Withdraw {
    constructor(customerId, fullName, balance, transactionAmount) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.balance = balance;
        this.transactionAmount = transactionAmount;
    }
}

class Transfer {
    constructor(senderId, senderName, email, recipientId, balance, transferAmount, fees, feesAmount, transactionAmount) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.email = email;
        this.recipientId = recipientId;
        this.balance = balance;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
    }
}

class Category {
    constructor(id, titleCategory) {
        this.id = id;
        this.titleCategory = titleCategory;
    }
}

class Product {
    constructor(id, title, category) {
        this.id = id;
        this.title = title;
        this.category = category;
    }
}

class User{
    constructor(id, roleId, fullName, phone, username, passwordHash) {
        this.id = id;
        this.roleId = roleId;
        this.fullName = fullName;
        this.phone = phone;
        this.username = username;
        this.passwordHash = passwordHash;
    }
}

class Order{
    constructor(id, user, status, totalFee, createAt, content) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.totalFee = totalFee;
        this.createAt = createAt;
        this.content = content;
    }
}

class Item{
    constructor(id, product, user, order, status, location, price, fee, quantity, startDate, updateDate) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.order = order;
        this.status = status;
        this.location = location;
        this.price = price;
        this.fee = fee;
        this.quantity = quantity;
        this.startDate = startDate;
        this.updateDate = updateDate;
    }
}

class ReportProblem{
    constructor(id, createAt, compensationFee, description, item, createBy) {
        this.id = id;
        this.createAt = createAt;
        this.compensationFee = compensationFee;
        this.description = description;
        this.item = item;
        this.createBy = createBy;
    }
}

class Role{
    constructor(id, roleTitle) {
        this.id = id;
        this.roleTitle = roleTitle;
    }
}

class Status{
    constructor(id, statusTitle) {
        this.id = id;
        this.statusTitle = statusTitle;
    }
}

const status = ["NEW", "BOOKED", "PAID", "CANCELLED"];

const role = ["USER", "ADMIN"];

function drawOptionStatus(temp){
    let str =``;
    for (let i = 1; i <= status.length; i++) {
        str += `<option value="${i}">${status[i-1]}</option>`;
    }
    $(temp).html(str);
}
