Zadaæa ORM
Napisati abstraktnu klasu "Model", koja sadrži dvije metode "String findByPk(Integer pk)" i "String findByAttribute(String a1, String a2)", koje na osnovu parametara kreiraju query string tipa "SELECT * FROM {table_name} WHERE id={pk}".
Naziv tabele je potrebno proslijediti u klasama koje naslijeðuju klasu Model.