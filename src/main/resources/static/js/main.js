function ValidateDeposit() {

	ValidateAccountType(document.depositForm);
}

function ValidateWithdraw() {

	ValidateAccountType(document.withdrawForm);
}

function ValidateTransferAccounts() {

	ValidateAccountType(document.transferAccountsForm);
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
