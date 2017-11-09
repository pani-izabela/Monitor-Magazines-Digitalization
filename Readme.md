##Project Title
Monitor-Magazines-Digitization to aplikacja do monitorowania postêpów w digitalizacji czasopism oraz liczenia kosztów tego procesu.
Opiera siê ona o bazê danych, zbudowan¹ z dwóch tabel. Pierwsza z nich zawiera czasopisma, które maj¹ byæ zdigitalizowane. Druga baza danych sk³ada siê z etapów digitalizacji. Aktualnie zak³ada ona istnienie 4 etapów. Ka¿dy z nich wi¹¿e siê z innym kosztem. Aby proces digitalizacji by³ zakoñczony, wszystkie tomy czasopisma musz¹ przejœæ przez ka¿dy jego etap. W danym momencie digitalizacja poszczególnych tomów czasopisma mo¿e byæ na innych etapach.

Aplikacja u³atwia uzyskanie informacji, ile pieniêdzy potrzeba w danym momencie na digitalizacjê wszystkich czasopis, jakie mamy w bazie. Informacja na temat kosztów i czasu, pozwala zaplanowaæ zatrudnienie pracowników w najbli¿szym okresie. Aplikacja u³atwia uzyskanie informacji o kosztach digitalizacji tak¿e jednego konkretnego tytu³u czasopisma, jest to przydatne np. w celu pisania wniosków o finansowanie takiego przedsiêwziêcia. Aplikacja u³atwia przygotowanie cotygodniowych raportów aktualnego stanu digitalizacji czasopism.

##Features
- aplikacja generuje raporty w formie csv i pdf
- pozwala na wyœwietlanie pojedynczego, jak i wszystkich czasopism
- pozwala na dodawanie czasopism do bazy, ich edycjê, a tak¿e usuwanie ich z bazy 
- liczy i zwraca koszty ka¿dego z etapów digitalizacji, a tak¿e podaje sumê kosztów tych etapów dla danego czasopisma. Liczy i zwraca koszty digitalizacji dla wszystkich czasopism w bazie
- liczy i zwraca liczbê godzin potrzebnych na realizacjê etapów III i IV, a tak¿e podaje sumê tego czasu dla danego czasopisma. Liczy i zwraca tak¿e czas potrzebny na digitalizacjê wszystkich czasopism w bazie. Etap I i II s¹ wykonywane przez podmiot zewnêtrzny, który jest op³acany w zale¿noœci od liczby artyku³ów i stron w danym tomie czasopisma. Podaje te¿ sumê kosztów tych etapów dla danego czasopisma.
- zwraca informacje na jakim etapie digitalizacji jest czasopismo, ile tomów przesz³o ju¿ przez konkretny etap, a ile jeszcze musi przez niego przejœæ, tj. ile tomów zosta³o zeskanowanych i ile czeka w kolejce do skanowania, ile tomów zosta³o opracowanych do formy du¿ego pdf-a i ile czeka na to opracowanie, ile tomów zosta³o opracowanych do formy ma³ego pdf-a i ile czeka na to opracowanie.
- zawiera testy jednostkowe dla metod serwisów i kontrolerów

##Built With
- Gradle
- MySQL
- framework Spring
- Lombok

##Authors
Izabela £ach

##Acknowledgments
Inspiracj¹ do napisania aplikacji, sta³y siê moje codzienne obowi¹zki zawodowe zwi¹zane z kordynacj¹ projektu - baza czasopism humanistycznych i spo³ecznych bazhum.muzhp.pl i bazhum.pl