# Temat: Temat: MojaChata.pl - aplikacja do wynajmowania mieszkań

### -----------PAP
## Zakres projektu
W ramach projektu zespół ma przygotować niewielką aplikację umożliwiającą tworzenie, edycję i wyszukiwanie informacji w zadanej przez prowadzącego bazie danych typu relacyjnego lub dokumentowego (NoSQL).
Na każdym etapie oprócz tworzonego kodu oceniane będą takie elementy jak projekt interfejsu użytkownika, zarządzanie źródłami, tworzenie testów automatycznych, ciągła integracja oprogramowania.

## Uruchomienie aplikacji
z głównego katalogu /app uruchamiamy w terminalu:
./mvnw spring-boot:run

w przeglądarce odpalamy:
http://localhost:8080

Gotowe!

## Wymagania
    • wykorzystanie bazy danych typu relacyjnego (W - 50%)
    • kod aplikacji napisany w języku java z wykorzystaniem framework'u spring (P)
    • interfejs umożliwia wykonywanie operacji:
        1. dodanie mieszkania na wynajem (W - 100%)
        2. usunięcie mieszkania na wynajem (W - 100%)
        3. zmiana parametrów mieszkania na wynajem (W - 100%)
        4. wyszukiwanie dostępnych mieszkań do wynajęcia (K - 90%)
        5. możliwość rezerwacji mieszkania, które chcemy wynająć (K - 80%)
        6. filtrowanie po cenie, rozmiarze i destynacji mieszkania (K - DONE)
        7. możliwość akceptacji lub odrzucenia zgłoszenia przez właściciela (P - 0%)
        8. dodanie opini (P -0%)
        9. usunięcie opini (P -0%)
        10. przeglądać tylko swoje mieszkania (T- 100%)
        11. logowanie do aplikacji (T - 80%)
        12. powiadomienie o zainteresowaniu mieszkaniem (zmiana koloru moje mieszkania)(T - 50%)
        13. * historia
        14. * anulowanie rezerwacji

## Terminy
    • 3.12 - działający prototyp
    • 14.01 - skończony projekt, czas na uwagi od prowadzącego
    • 28.01 - ostateczny termin na oddanie projektu

## Członkowie zespołu:
    • Weronika Maślana (wyżej W)
    • Katarzyna Kanicka (wyżej K)
    • Paweł Marton (wyżej P)
    • Tomasz Foryś (wyżej T)



### -----------BD1
## Cel projektu
Zadanie polega na realizacji projektu bazy danych pod wybrane zagadnienie. W ramach projektu należy uszczegółowić zakres pracy.

## Wymagania:
    1. model ER (z nazwanymi związkami między encjami), model relacyjny (W)
    2. skrypty DDL do stworzenia schematu bazy danych,
    3. skrypty do załadowania danych,
    4. definicje sekwencji, wyzwalaczy, procedur, funkcji,
    5. skrypty testujące działanie zaprojektowanej bazy,
    6. aplikację napisaną w języku JAVA oraz z wykorzystaniem technologii JDBC lub JPA. Aplikacja ma pozwolić na prostą komunikację/pobieranie danych z zaimplementowanej bazy. W przypadku realizacji łączonej z projektem z przedmiotu PAP, aplikacja powinna być zgodna z wymaganiami z projektu z przedmiotu PAP.
    7. Minimalna liczba tabel dla projektów zespołowych (3- 4 osoby): 12 (W)
    8. krótki opis rozwiązania,
    9. analizę rozwiązania (znane ograniczenia, możliwości dalszego rozwoju oraz inne wnioski),

## Punktacja:
    • Opis tekstowy projektu (wraz z krytyczną analizą rozwiązania): 2 pkt.
    • Model ER i relacyjny, skrypty DDL, skrypty ładujące dane: 2 pkt.
    • Wyzwalacze, procedury, funkcje, kursory, minimum 2 procedury, 2 funkcje, 2 wyzwalacze, wykorzystanie kursorów: 2 pkt.
    • Skrypty testujące: 2 pkt. (skrypty powinny zawierać nietrywialne zapytania wykorzystujące różne rodzaje złączeń, filtrowanie, grupowanie danych a także zaimplementowane funkcje, wywołania procedur, prezentacje działania triggerów).
    • Aplikacja w JAVA/Python (ewentualnie innej technologii uzgodnionej z prowadzącym projekt): 2 pkt.

 Każdy członek projektu jest oceniany indywidualnie.

## Terminy
Realizowanie projektu należy zakończyć do dnia 22 stycznia 2025 godz 23:59.

## Członkowie zespołu:
    • Weronika Maślana (wyżej W)
    • Katarzyna Kanicka (wyżej K)
    • Tomasz Foryś (wyżej T)
(Paweł już ma zaliczone bazy danych)