package server.command.`object`

import server.product.*

class ProductBuilderScript : ProductBuilder() {
    private val product = Product.Builder()

    private lateinit var argumenstFromUser: MutableList<String>



    override fun build(args: Any?): Product? {

        argumenstFromUser = args as MutableList<String>
        setName()
        setCoordinate()
        setPrice()
        setUnitOfMeasure()
        setOrganization()
        return product.build()


    }

    private fun setName(){
        readLineUntilCondition(argumenstFromUser[0]) { true }?.let { product.name(it) }
    }

    private fun setCoordinate(){
        val x = readLineUntilCondition(argumenstFromUser[1]) { s: String -> s.toIntOrNull() != null}
        val y = readLineUntilCondition(argumenstFromUser[2]) { s: String -> s.toDoubleOrNull() != null}
        if (x != null && y != null) {
            product.coordinates(Coordinates(x.toInt(),y.toDouble()))
        }
    }

    private fun setPrice(){
        val price = readLineUntilCondition(argumenstFromUser[3]) { s: String -> s.toIntOrNull() != null }
        if (price != null) {
            product.price(price.toInt())
        }
    }
    private fun setUnitOfMeasure(){
        fun checkIfUnitOfMeasure(s:String): Boolean {
            try {
                UnitOfMeasure.valueOf(s)
            } catch (e: IllegalArgumentException){
                return false
            }
            return true
        }


        val unitOfMeasure = readLineUntilCondition(argumenstFromUser[4]) { s:String -> checkIfUnitOfMeasure(s) }
        unitOfMeasure?.let { UnitOfMeasure.valueOf(it) }?.let { product.unitOfMeasure(it) }
    }

    private fun setOrganization() {
        fun checkIfOrganizationType(s:String):Boolean{
            try {
                OrganizationType.valueOf(s)
            } catch (e: IllegalArgumentException){
                return false
            }
            return true
        }



        val organization = Organization.Builder()
        readLineUntilCondition(argumenstFromUser[5]) { true }?.let { organization.name(it) }
        readLineUntilCondition(argumenstFromUser[6]) {true}?.let { organization.fullName(it) }
        val organizationType = readLineUntilCondition(argumenstFromUser[7]) { s: String -> checkIfOrganizationType(s)}
        organizationType?.let { OrganizationType.valueOf(it) }?.let { organization.type(it) }
        organization.build()?.let { product.manufacturer(it) }
    }


    private fun readLineUntilCondition(messageFromUser: String, condition: (String) -> Boolean): String?{
            if(messageFromUser.isNotEmpty()){
                if(condition(messageFromUser)){
                    return messageFromUser
                }
            }
            return null
    }
}