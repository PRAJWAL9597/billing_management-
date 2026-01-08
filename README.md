# billing_management-

rent and bill management for apartment owners

building production grade apartment rent and electricity bill management where
Owner enters monthly meter readings

1) System will automatically calculates electricity consumption<br>
2) split common area usage<br>
3) applies room rent maintenance penalties generates a monthly invoice per tenant<br>
4) pricing rules can change over time

<h1>Architecture</h1>

<h3>we are using<h3>
<h3>SpringBoot + PostgreSQL + Flyway</h3>

Progress :-

Spring boot app is running clearly<br>
Github repo connected and synced

<h1> Database Versioning(Flyway)</h1>

Billing systems cant rely on auto DDL<br>
Schema must be reproducible and auditable

Migration run automatically on startup<br> 
App wont start if schema is wrong

<h1>Core Domain Entites</h1>

<h3>Tenant</h3>
Prepresents Person renting room<br>
Active inactive flag<br>
independent of billing logic 

<h3>MeterReading</h3>
Stores Monthly readings<br>
contains tenant month previous and currunt reading<br>
but it doesnt calculate bills 

<h3>Pricing Policy</h3>
Stores owner controlled values<br>
unit price<br>
common area units<br>
maintenance<br>
effective date<br>
Design to support price changes over time 