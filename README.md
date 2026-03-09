#  CFR Manager - Aplicație Web de Gestiune Feroviară (Python)

CFR Manager este o aplicație web dezvoltată în Python folosind framework-ul Flask, menită să digitalizeze și să simplifice gestionarea biletelor de tren, a călătorilor și a sucursalelor CFR. 

Proiectul implementează operațiuni complete de tip **CRUD** (Create, Read, Update, Delete) și utilizează o bază de date relațională MySQL, protejând integritatea datelor prin tranzacții sigure și constrângeri de unicitate.

##  Funcționalități Principale

* **Gestiunea Călătorilor:** Adăugare, editare și ștergere a datelor pasagerilor. Include validare strictă pe formatul și lungimea CNP-ului (fix 13 cifre).
* **Gestiunea Sucursalei:** Administrarea punctelor fizice de vânzare a biletelor. Previne duplicarea sucursalelor cu același nume în același oraș.
* **Emiterea Biletelor:** Sistem inteligent de emitere a biletelor folosind liste derulante (dropdowns) populate dinamic cu datele călătorilor și sucursalelor.
* **Sistem Anti-Double Booking:** Previne la nivel de bază de date rezervarea aceluiași loc, în același vagon/tren și în aceeași zi, gestionând conflictele elegant prin mecanisme de `rollback`.

##  Tehnologii Utilizate

* **Backend:** Python 3.x, Flask (Micro-framework)
* **Bază de Date:** MySQL
* **ORM (Object Relational Mapper):** Flask-SQLAlchemy
* **Driver DB:** PyMySQL, Cryptography
* **Frontend:** HTML5, Jinja2 (Templating Engine), Bootstrap 5, CSS

##  Structura Bazei de Date

Aplicația folosește baza de date `cfr_db_web` care conține 3 tabele interconectate (aflate în Forma Normală 3):
1. `calatori` (Părinte)
2. `sucursale` (Părinte)
3. `bilet` (Copil/Entitate Asociativă cu constrângere `UNIQUE` pe rută/loc/dată).

##  Instrucțiuni de Instalare și Rulare

### 1. Pre-rechizite
* Ai nevoie de [Python 3.x](https://www.python.org/downloads/) instalat.
* Ai nevoie de un server MySQL local pornit (ex: via **XAMPP**, **WAMP** sau **MySQL Workbench**).
* pip install flask flask-sqlalchemy pymysql cryptography

#  CFR Manager - Sistem de Gestiune Feroviară (Java)

O aplicație web Full-Stack dezvoltată în **Java Spring Boot** pentru administrarea bazei de date a unei companii feroviare. Sistemul permite gestionarea călătorilor, a sucursalelor și emiterea biletelor de tren, asigurând integritatea datelor printr-un sistem tranzacțional robust.

##  Funcționalități Principale

* **Gestiunea Călătorilor (CRUD):**  Adăugare, editare, vizualizare și ștergere călători.
  * Validări stricte: CNP-ul trebuie să aibă exact 13 cifre. Nu se pot înregistra doi călători cu același CNP sau Email.
* **Gestiunea Sucursalelor (CRUD):**  Administrarea punctelor fizice de vânzare.
  * Validare logică: Nu se pot adăuga două sucursale cu același nume în același oraș.
* **Emiterea Biletelor (Tranzacțional):** Formular dinamic cu dropdown-uri pre-populate din baza de date.
  * **Sistem Anti-Double Booking:** Aplicația previne vânzarea aceluiași loc (Tren + Vagon + Loc + Dată) către două persoane diferite, afișând mesaje de eroare specifice.
* **Interfață Modernă:** Design responsive și intuitiv, cu feedback vizual (erori evidențiate cu roșu) la introducerea greșită a datelor.

##  Tehnologii Utilizate

**Backend:**
* Java 17
* Spring Boot 3.x (Spring Web, Spring Validation)
* Spring Data JPA (Hibernate)
* Maven (Build Tool)

**Bază de Date:**
* MySQL Server 8.0
* JDBC Driver

**Frontend:**
* Thymeleaf (Motor de template-uri)
* HTML5 / CSS3
* Bootstrap 5 (UI Framework)
* FontAwesome (Iconițe)

##  Structura Bazei de Date
Aplicația folosește 3 tabele relaționate:
1. `calatori` - Stochează datele clienților (1:N cu biletele).
2. `sucursale` - Stochează locațiile agențiilor (1:N cu biletele).
3. `bilet` - Tabela de asociere care conține datele tranzacției și asigură integritatea locurilor rezervate.

##  Instrucțiuni de Instalare și Rulare

Pentru a rula acest proiect local, urmează pașii de mai jos:

### 1. Pre-rechizite
* Ai nevoie de **Java JDK 17** (sau mai nou) instalat.
* Ai nevoie de **MySQL Server** instalat și pornit.

