
interface Osoba {
    fun identitetOsobe() : String
    fun titula() : String
}
open class Inzenjer(
    var ime : String,
    var prezime : String,
    var godIskustva : Int,
    var titula : String,
    var ekspertize : List<String>

) : Osoba {

    init {
        if(godIskustva < 0){
            println("Godine iskustva ne mogu biti negativne, stoga ce biti postavljene na 0 !")
            godIskustva = 0
        }

        if(ekspertize.isEmpty()){
            println("Lista ekspertiza je prazna! Zbog toga cemo dodati vrijednost 'nema eksertizu' ")
            ekspertize = listOf("Nema ekspertizu")
        }
    }

    override fun identitetOsobe() : String {
        return "$ime $prezime"
    }

    override fun titula() : String{
        return titula
    }

    open fun ispisInfo(){
        println("Inzenjer: $ime $prezime, godine iskustva: $godIskustva, titula: $titula")
    }
}


class softverskiInzenjer(
    ime : String,
    prezime : String,
    godIskustva : Int,
    ekspertize : List<String>,
    var brProjekata : Int
) : Inzenjer(ime, prezime, godIskustva, "Softverski inzenjer", ekspertize) {

    init{
        if(brProjekata < 0){
            println("Broj projekata postavljen na vrijednost manju od nule, to nece proci. Postavljam ga na nulu.")
            brProjekata = 0
        }
    }

    override fun identitetOsobe() : String {
        return "$ime $prezime"
    }

    override fun titula() : String{
        return titula
    }

    override fun ispisInfo(){
        println("Inzenjer: $ime $prezime, godine iskustva: $godIskustva, titula: $titula, broj projekata: $brProjekata")
    }

    fun uspjesnost(){
        val iskustvo = when(brProjekata) {
            in 0..5 -> "Slaba uspjesnost"
            in 6..10 -> "Solidna uspjesnost"
            in 11..15 -> "Sjajna uspjesnost"
            in 16..20 -> "Brutalna uspjesnost"
            else -> "Gazda/rica"
        }
        println("Uspjesnot projekata inzenjera je: $iskustvo")
    }


}


class inzenjerElektrotehnike(
    ime : String,
    prezime : String,
    godIskustva : Int,
    ekspertize : List<String>,
    var brCertifikata : Int
) : Inzenjer(ime, prezime, godIskustva, "Inzenjer elektrotehnike", ekspertize){

    init{
        if(brCertifikata < 0){
            println("Broj certifikata postavljen na vrijednost manju od nule, to nece proci. Postavljam ga na nulu.")
            brCertifikata = 0
        }
    }

    override fun identitetOsobe() : String {
        return "$ime $prezime"
    }

    override fun titula() : String{
        return titula
    }

    override fun ispisInfo(){
        println("Inzenjer: $ime $prezime, godine iskustva: $godIskustva, titula: $titula, broj certifikata: $brCertifikata")
    }

    fun uspjesnost(){
        val iskustvo = when(brCertifikata) {
            in 0..5 -> "Slabo"
            in 6..10 -> "Solidno"
            in 11..15 -> "Sjajno"
            in 16..20 -> "Brutalno"
            else -> "Gazda/rica"
        }
        println("Na osnovu broja stecenih certifikata, mozemo reci da je iskustvo inzenjera $iskustvo")
    }
}


fun grupisanjeSaFold(listaInzenjera : List<Inzenjer>) : Map<String, List<Inzenjer>>{

    return listaInzenjera.fold(mutableMapOf<String, MutableList<Inzenjer>>()){acc,
                                                                              inzenjer -> if(inzenjer.godIskustva > 5){
        inzenjer.ekspertize.forEach{ekspertiza -> acc.getOrPut(ekspertiza) {mutableListOf()}.add(inzenjer)}
    }
        acc
    }
}

fun softIngReduce(inzenjeri : List<softverskiInzenjer>) : softverskiInzenjer? {
    return inzenjeri.reduce{najiskusniji,
                            n -> if(najiskusniji.godIskustva < n.godIskustva)
        n else najiskusniji
    }
}

fun IngElektroReduce(inzenjeri : List<inzenjerElektrotehnike>) : inzenjerElektrotehnike? {
    return inzenjeri.reduce{najiskusniji,
                            n -> if(najiskusniji.godIskustva < n.godIskustva)
        n else najiskusniji
    }
}

fun agregati(softIng : List<softverskiInzenjer>, ingElektro : List<inzenjerElektrotehnike>) : Int {
    var ukBroj : Int = 0
    for(x in softIng){
        ukBroj += x.brProjekata
    }

    for(y in ingElektro){
        ukBroj += y.brCertifikata
    }

    return ukBroj
}

fun ispisSvihInzenjera(lista : List<Inzenjer>){
    for( x in lista ){
        x.ispisInfo()
    }
}


fun main() {
    val listaInzenjera : List<Inzenjer> = listOf(
        inzenjerElektrotehnike("Edin", "Hodzic", 3, listOf("C", "C++", "Embedded C"), 11),
        inzenjerElektrotehnike("Amer", "Imamovic", 7, listOf("C", "Embedded C", "VHDL"), 26),
        inzenjerElektrotehnike("Ensar", "Cehajic", 6, listOf("C", "Java", "Python"), 17),
        inzenjerElektrotehnike("Ajdin", "Hercinovic", 3, listOf("Python", "Radijski sistemi", "Kotlin"), 14),

        softverskiInzenjer("Irmel", "Haskic", 2, listOf("C++", "C#", "Haskell"), 9),
        softverskiInzenjer("Nevzeta", "Smajic", 27, listOf("PHP", "Machine learning"), 47),
        softverskiInzenjer("Dzenan", "Cerkezovic", 1, listOf("VHDL","Python"), 2)
    )

    ispisSvihInzenjera(listaInzenjera)

    val grupSaFold = grupisanjeSaFold(listaInzenjera)
    println("Inzenjeri sa vise od 5 godina iskustva grupisani sa FOLD: ")
    grupSaFold.forEach{(ekspertiza, listaInzenjera) -> val imena = listaInzenjera.joinToString{it.ime}
        println("Ekspertiza '$ekspertiza' (broj: ${listaInzenjera.size}): $imena")
    }

    val znajuC = grupSaFold["C"] ?: emptyList()
    val provjeraFolda = znajuC.all{it.godIskustva >= 5}
    if(provjeraFolda)
        println("Fold radi kako treba, svi probrani inzenjeri imaju barem 5 godina iskustva.")
    else
        println("Fold NE radi kako treba, potrebno ga je popraviti")


    val softverasi = listaInzenjera.filterIsInstance<softverskiInzenjer>()
    val elektro = listaInzenjera.filterIsInstance<inzenjerElektrotehnike>()
    val najjaciSoft = softIngReduce(softverasi)
    val najjaciElektro = IngElektroReduce(elektro)
    println("Najiskusniji sofverski inzenjer je: ${najjaciSoft?.ime} ${najjaciSoft?.prezime}")
    println("Najiskusniji inzenjer elektrotehnike je: ${najjaciElektro?.ime} ${najjaciElektro?.prezime}")

    if(najjaciElektro?.ime == "Amer" && najjaciElektro?.prezime == "Imamovic"){
        println("Uspjesna pretraga najuspjensnijeg inzenjera elektrotehnike")
    }
    if(najjaciSoft?.ime == "Nevzeta" && najjaciSoft?.prezime == "Smajic"){
        println("Uspjesna pretraga najuspjensnijeg sofverskog inzenjera")
    }

    val zbirProjCert = agregati(softverasi, elektro)
    println("Zbir projekata i certifikata inzenjera je: ${zbirProjCert}")

    val provZbira = 11 + 26 + 17 + 14 + 9 + 47 + 2
    if(zbirProjCert == provZbira){
        println("Uspjesno izracunat zbir")
    }
    else
        println("Lose izracunat zbir")



}
