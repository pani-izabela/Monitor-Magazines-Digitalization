##Project Title
Monitor-Magazines-Digitization to aplikacja do monitorowania post�p�w w digitalizacji czasopism oraz liczenia koszt�w tego procesu.
Opiera si� ona o baz� danych, zbudowan� z dw�ch tabel. Pierwsza z nich zawiera czasopisma, kt�re maj� by� zdigitalizowane. Druga baza danych sk�ada si� z etap�w digitalizacji. Aktualnie zak�ada ona istnienie 4 etap�w. Ka�dy z nich wi��e si� z innym kosztem. Aby proces digitalizacji by� zako�czony, wszystkie tomy czasopisma musz� przej�� przez ka�dy jego etap. W danym momencie digitalizacja poszczeg�lnych tom�w czasopisma mo�e by� na innych etapach.

Aplikacja u�atwia uzyskanie informacji, ile pieni�dzy potrzeba w danym momencie na digitalizacj� wszystkich czasopis, jakie mamy w bazie. Informacja na temat koszt�w i czasu, pozwala zaplanowa� zatrudnienie pracownik�w w najbli�szym okresie. Aplikacja u�atwia uzyskanie informacji o kosztach digitalizacji tak�e jednego konkretnego tytu�u czasopisma, jest to przydatne np. w celu pisania wniosk�w o finansowanie takiego przedsi�wzi�cia. Aplikacja u�atwia przygotowanie cotygodniowych raport�w aktualnego stanu digitalizacji czasopism.

##Features
- aplikacja generuje raporty w formie csv i pdf
- pozwala na wy�wietlanie pojedynczego, jak i wszystkich czasopism
- pozwala na dodawanie czasopism do bazy, ich edycj�, a tak�e usuwanie ich z bazy 
- liczy i zwraca koszty ka�dego z etap�w digitalizacji, a tak�e podaje sum� koszt�w tych etap�w dla danego czasopisma. Liczy i zwraca koszty digitalizacji dla wszystkich czasopism w bazie
- liczy i zwraca liczb� godzin potrzebnych na realizacj� etap�w III i IV, a tak�e podaje sum� tego czasu dla danego czasopisma. Liczy i zwraca tak�e czas potrzebny na digitalizacj� wszystkich czasopism w bazie. Etap I i II s� wykonywane przez podmiot zewn�trzny, kt�ry jest op�acany w zale�no�ci od liczby artyku��w i stron w danym tomie czasopisma. Podaje te� sum� koszt�w tych etap�w dla danego czasopisma.
- zwraca informacje na jakim etapie digitalizacji jest czasopismo, ile tom�w przesz�o ju� przez konkretny etap, a ile jeszcze musi przez niego przej��, tj. ile tom�w zosta�o zeskanowanych i ile czeka w kolejce do skanowania, ile tom�w zosta�o opracowanych do formy du�ego pdf-a i ile czeka na to opracowanie, ile tom�w zosta�o opracowanych do formy ma�ego pdf-a i ile czeka na to opracowanie.
- zawiera testy jednostkowe dla metod serwis�w i kontroler�w

##Built With
- Gradle
- MySQL
- framework Spring
- Lombok

##Authors
Izabela �ach

##Acknowledgments
Inspiracj� do napisania aplikacji, sta�y si� moje codzienne obowi�zki zawodowe zwi�zane z kordynacj� projektu - baza czasopism humanistycznych i spo�ecznych bazhum.muzhp.pl i bazhum.pl