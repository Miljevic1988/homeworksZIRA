NETWORKING PART II
TASK – SERVER ZA UPLOAD LINKOVA

Napraviti jedan HTTP server koji uèitaje File sa listom linkova i imena linkova, npr. ime je Google, a

link je https://google.ba. HTTP server æe poslati klijentu HTML file koji sadrži te linkove predstavljene

kao prave HTML linkove (pomoæu <a href...).

Napraviti jedan normalni server koji prima od klijenta dva String-a, jedan za link, a drugi za ime

linka. Server mora provjeriti da li je dati link validan i postojeæi. Ukoliko zadovoljava oba uslova onda

zapisati dati link i dato ime u File (koji æe HTTP server kasnije èitati).