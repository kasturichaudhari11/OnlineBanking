function ValidateDeposit() {

	ValidateAccountType(document.depositForm);
}

function ValidateWithdraw() {

	ValidateAccountType(document.withdrawForm);
}

function ValidateTransferAccounts() {

	if (document.transferAccountsForm.transferFrom.value == "") {
		alert("Please provide your account!");

		name_error.textContent = "Account selection is required!";
		transferAccountsForm.transferFrom.focus();
		return false;
	}

	if (document.transferAccountsForm.transferTo.value == "") {
		alert("Please provide your account!");

		name_error.textContent = "Account selection is required!";
		transferAccountsForm.transferTo.focus();
		return false;
	}

	if (document.transferAccountsForm.transferFrom.value == document.transferAccountsForm.transferTo.value) {
		alert("You cannot transfer within same account!");

		name_error.textContent = "Different account selection is required!";
		transferAccountsForm.transferTo.focus();
		return false;
	}

	return true;
}

function ValidateAccountType(accountOperationForm) {

	if (accountOperationForm.accountType.value == "") {
		alert("Please provide your account!");

		name_error.textContent = "Account selection is required!";
		accountOperationForm.accountType.focus();
		return false;
	}

	return true;
}


