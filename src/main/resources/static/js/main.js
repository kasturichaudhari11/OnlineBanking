function ValidateDepositOrWithdraw() {

	if (document.accountOperationForm.accountType.value == "") {
		alert("Please provide your account!");

		name_error.textContent = "Account selection is required!";
		document.accountOperationForm.accountType.focus();
		return false;
	}

	return true;
}
