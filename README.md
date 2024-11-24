# Temat: Temat: MojaChata.pl - aplikacja do wynajmowania mieszkań

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
    • wykorzystanie bazy danych typu relacyjnego (W - DONE)
    • kod aplikacji napisany w języku java z wykorzystaniem framework'u spring (P)
    • interfejs umożliwia wykonywanie operacji:
        1. dodanie mieszkania na wynajem (W - DONE)
        2. usunięcie mieszkania na wynajem (W - DONE)
        3. zmiana parametrów mieszkania na wynajem (W - DONE)
        4. wyszukiwanie dostępnych mieszkań do wynajęcia (K - 90%)
        5. możliwość rezerwacji mieszkania, które chcemy wynająć (K - 80%)
        6. filtrowanie po cenie, rozmiarze i destynacji mieszkania (K - DONE)
        7. możliwość akceptacji lub odrzucenia zgłoszenia przez właściciela (P)
        8. dodanie opini (P)
        9. usunięcie opini (P)
        10. przeglądać tylko swoje mieszkania (T)
        11. logowanie do aplikacji (T)
        12. powiadomienie o zainteresowaniu mieszkaniem (zmiana koloru moje mieszkania)(T)
        13. * historia
        14. * anulowanie rezerwacji

## Członkowie zespołu:
    • Weronika Maślana (wyżej W)
    • Katarzyna Kanicka (wyżej K)
    • Paweł Marton (wyżej P)
    • Tomasz Foryś (wyżej T)