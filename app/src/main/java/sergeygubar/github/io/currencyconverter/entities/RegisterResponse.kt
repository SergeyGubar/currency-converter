package sergeygubar.github.io.currencyconverter.entities

data class RegisterResponse(val success: String) {
    val isSuccess
        get() = success.toLowerCase() == "true"
}