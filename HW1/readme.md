RMAS2025 - Zadaca 1

U Zadaci 1 iz predmeta Razvoj mobilnih aplikacija i servisa, zadatak B, cilj je bio da ispitamo i unaprijedimo svoje poznavanje
rada sa interface-ima, klasama, nasljedjivanjem i odredjenim funkcionalnostima.

Napravljen je interface Osoba, koji samo deklarise dvije metode, jednu za vracanje identiteta osobe, i jednu za vracanje titule osobe. 
Ovo mi djeluje pomalo i bespotrebno u ovom zadatku u smislu funkcionalnosti svega, ali sta je tu je.

Klasa Inzenjer ce biti roditeljska klasa, kao sto cemo vidjeti u nastavku koda. Sadrzi ime, prezime, godine iskustva, titulu, i ekspertize za svakog inzenjera.
Odradjena je i validacija podataka, koristenjem INIT bloka, koji se "provjerava" prilikom inicijalizacije objekta klase. Provjerava se da li su godine iskustva manje od 0 i da
li je lista ekspertiza prazna.
Takodjer su implementirane i obje funkcije iz interfacea Osoba (neophodno bilo koristiti override), kao i funkcija ispisInfo(), za ispis svih informacija o pojedinacnom inzenjeru.

Dalje su kreirane dvije podklase prethodne klase, sofverskiInzenjer i inzenjerElektrotehnike. Sve je identicno roditeljskoj klasi, osim toga sto je dodan broj projekata za soft. ing. i 
broj certifikata za ing.ee.. Takodjer, osim naslijednjenih funkcija je implementirana i funkcija uspjesnost(), koja provjerava broj certifikata ili projekata
i na osnovu toga odlucuje koji je nivo strucnosti i iskustva inzenjera.

GRUPISANJE SA FOLD I REDUCE

U ovom dijelu sam se morao posluziti uslugama AI-a, zbog nejasnoce u postavljanju zadatka, tj. zbog cinjenice da nikada nisam koristio ove funkcije, a posto je AI mozda i najlaksi nacin da 
saznam bitne stvari o njima, odlucio sam pomoci sam sebi. Iskoristen je samo za upoznavanje sa funkcionalnostima fold() i reduce().

Kako funkcionise grupisanje po ekspertizi sa fold():
Funkcija prima listu inzenjera, vraca mapu koja se sastoji od kljuca tipa string i elementa tipa lista inzenjera. 
Funkcija vraca rezultat djelovanja fold() nad proslijednjenom listom inzenjera.
Rezultat se dobija tako sto postavimo da je inicijalni akumulator prazna mapa, filtriramo inzenjera (izdvajamo one sa barem 5 godina iskustva), te onda iteriranjem kroz ekspertize 
dodajemo odgovarajuce inzenjere u listu za tu ekspertizu. String u mapi predstavlja pojedinacnu ekspertizu, a lista je lista inzenjera sa tom ekspertizom i iskustvom vecim od 5 godina.

Grupisanje sa reduce() je prilicno jednostavno, podijelili smo ovo na dvije funkcije, jednu za soft.ing. i jednu za ing.ee..
U obje se jednostavnim iteriranjem kroz clanove liste i poredjenjem njihovog iskustva dolazi do odgovora ko je najiskusniji u kojoj domeni.

GRUPISANJE SA AGGREGATE

S obzirom da Kotlin nema svoju aggregate() funkciju, napravio sam je ja. Funkcija je implementirana tako da prima dvije liste, jednu za soft.ing. i drugu za ing.ee..
Prolazi kroz obje liste, sabira vrijednosti broja certifikata i projekata, te na kraju vraca ukupan broj.

Ove tri funkcije sluze za obradu kolekcije podataka, te ih po potrebi svode na jednu vrijednost. Dakle, bave se akumulacijom podataka.
Sada se postavlja pitanje, kako znati koju od ovih funkcija trebamo koristiti?

  -> fold() koristimo kada trebamo napraviti novu kolekciju od vec postojece, kao sto smo imali u zadatku, napravili smo mapu, a inicijalno smo imali listu.
      Takodjer kada pocinjemo akumulaciju od nulte vrijednosti, tada koristimo fold(), kao i kada rezultat nije istog tipa kao elementi nase kolekcije.

  -> reduce() koristimo za pronalazak maksimuma, minimuma ili pak sume iz nase kolekcije, kao sto smo u zadatku trazili najiskusnijeg od nasih inzenjera. 
      Koristimo je i kada rezultat treba biti istog tipa kao i elementi kolekcije.

  -> aggregate() je najprakticniji kada trebamo izracunati zbir nekih razlicitih elemenata, npr. u zadatku smo sumirali ukupan broj certifikata i projekata za inzenjere. 


POKRETANJE PROGRAMA

Link za Kotlin Playground: https://pl.kotl.in/xjwTl_An4
Na ovom linku mozete pronaci kod za moj zadatak. Ovo sam stavio cisto iz predostroznosti, ukoliko bude problema sa otvaranjem glavnog file-a.


Edin Hodzic, 22167
