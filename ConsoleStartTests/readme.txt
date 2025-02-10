Tieto .bat skripty slúžia na automatizované spustenie testov v rámci projektu AppiumProject využitím Maven profilov: AllTests, CreateContactsTests a DeleteContactsTests. Skript najskôr nastaví názov konzolového okna, následne pridáva oneskorenie pomocou TIMEOUT príkazov a následne prejde do konkrétneho adresára projektu pred spustením Maven testov.

Dôležitá poznámka:

Pevne nastavená cesta cd C:\Users\mku\IdeaProjects\AppiumProject musí byť manuálne upravená podľa skutočného umiestnenia projektu na konkrétnom počítači. Po tejto úprave sa testy úspešne spustia. 