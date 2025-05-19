# MojaChata.pl - aplikacja do wynajmowania mieszkań

- [English](README.md)

## Na przedmiot PAP

## Autorzy:
- Weronika Maślana
- Katarzyna Kanicka
- Tomasz Foryś

## Zakres projektu
W ramach projektu zespół ma przygotować niewielką aplikację umożliwiającą tworzenie, edycję i wyszukiwanie informacji w zadanej przez prowadzącego bazie danych typu relacyjnego lub dokumentowego (NoSQL).
Na każdym etapie oprócz tworzonego kodu oceniane będą takie elementy jak projekt interfejsu użytkownika, zarządzanie źródłami, tworzenie testów automatycznych, ciągła integracja oprogramowania.

## Uruchomienie aplikacji
z głównego katalogu /app uruchamiamy w terminalu:
`./mvnw spring-boot:run`

w przeglądarce odpalamy:
`http://localhost:8080`

Gotowe!

### Uruchomienie testów
Z głównego katalogu /app uruchamiamy w terminalu:
`./mvnw test`

## Architektura
- baza danych Oracle
- backend w Java z wykorzystaniem frameworku Spring
- frontend w Thymeleaf

## Funkcjonalności
1. dodanie mieszkania na wynajem
2. usunięcie mieszkania na wynajem
3. zmiana parametrów mieszkania na wynajem
4. wyszukiwanie dostępnych mieszkań do wynajęcia
5. możliwość rezerwacji mieszkania, które chcemy wynająć
6. filtrowanie po cenie, rozmiarze i destynacji mieszkania
7. możliwość akceptacji lub odrzucenia zgłoszenia przez właściciela
8. dodanie opini
9. usunięcie opinii
10. przeglądać tylko swoje mieszkania
11. logowanie do aplikacji
12. powiadomienie o zainteresowaniu mieszkaniem (informacja dla właściciela mieszkania o tym, że ma request do swojego mieszkania. Prezentuje się w postaci migającego przycisku moje mieszkania)
13. * historia
14. * anulowanie rezerwacji
