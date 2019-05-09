# Analiza-kurs-w-walut \n
Definicja zadania realizowanego w projekcie
Analizując kursy walut i ceny wymiany w kantorach, zauważyli, że przy sprzyjających okolicznościach na wymianie waluty można zarobić.
Dla przypadkowych słuchaczy zawsze mieli pod ręką kartkę z rozpisanym przykładem.

Nazwy walut: euro (EUR), funty brytyjskie (GBP), dolary amerykańskie (USD).

Kursy wymiany:
EUR/GBP: 0,8889
GBP/USD: 1,2795
EUR/USD: 1,1370
USD/EUR: 0,8795

W tym przypadku jeśli chcemy wymienić 1000 EUR na USD, to można postąpić na dwa sposoby.
I. Bezpośrednia wymiana EUR -> USD po kursie 1,1370

1000 * 1,1370 = 1137 USD

II. Pośrednia wymiana EUR -> GBP -> USD

1000 * 0,8889 = 888,9 GBP
888,9 * 1,2795 = 1137,34755 USD

Swojemu zleceniobiorcy wyznaczyli dwa zadania:
a.) wykrywanie korzystnej ścieżki wymiany waluty dla wskazanej waluty;
b.) wykrywanie sytuacji ekonomicznego arbitrażu, czyli kombinacji, gdy kursy walut są tak ułożone, aby (w pokazanym przykładzie) wymiana EUR -> GBP -> USD -> EUR zwracała więcej niż kwota wejściowa (więcej niż 1000 EUR dla omawianego przykładu).

Przykład pliku dostarczanego przez GW do zleceniobiorcy.

 Waluty (id | symbol | pełna nazwa)
0 EUR euro
1 GBP funt brytyjski
2 USD dolar amerykański
 Kursy walut (id | symbol (waluta wejściowa) | symbol (waluta wyjściowa) | kurs | typ opłaty | opłata)
0 EUR GBP 0,8889 PROC 0,0001
1 GBP USD 1,2795 PROC 0
2 EUR USD 1,1370 STAŁA 0.025
3 USD EUR 0,8795 STAŁA 0.01

Wynikiem działania programu:
a) w przypadku wybrania trybu najkorzystniejszej wymiany waluty (1000 EUR -> USD):

wartość początkowa -> ciąg kolejnych wymian -> wartość końcowa

1000 EUR -> GBP -> USD -> 1137,34755 USD

b) w przypadku wybrania trybu wyszukania dowolnego arbitrażu (przy podanej wielkości, np. 1000):

1000 waluta wejściowa -> ciąg kolejnych wymian -> wartość końcowa

1000 EUR -> GBP -> USD -> EUR -> 1000,297170225 EUR

