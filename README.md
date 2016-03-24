#JSON Client-Sever komunikacija
Cilj:

Potrebno je napraviti Client-Server aplikacije (Client i Server), koje međusobno komuniciraju.

Komunikacija se odvije putem JSON stringa.




Client šalje upit na Server, a Server na taj upit odgovara sa random rečenicom iz fajla.

Potrebno je u file upisati određeni broj rečenica, koje će Server randomly čitati vraćati na Client.

Server prvo instancira BitResponse objekat, koji nakon toga pretvara u JSON i vraća na Client.




Odgovor Servera je u JSON formatu.




Client čita JSON i pretvara ga u BitResponse objekat.




Specifikacije:

BitRespones objekat se sastoji od:

- message (String)

- timestamp (Date)




JSON izgleda ovako:

{

"message": "Vaša poruka",

"timestamp": "2015-07-31 17:55:00"

}




Za ovaj zadatak, potrebno je korisiti Jackson biblioteku.
