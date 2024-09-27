# TP1-POD: Sala de Emergencias
# Grupo 5


## Instrucciones de Compilación
Para compilar el proyecto, se deben ejecutar los siguientes comandos en la raíz del proyecto:
```bash
chmod +x compile.sh
./run-program.sh
```

## Instrucciones de Ejecución
### Servidor
Para ejecutar el servidor, se deben ejecutar los siguientes comandos en la carpeta raíz del proyecto:
```bash
cd ./tmp/tp1-g5-server-2024.2Q/

./run-server.sh
```

### Clients
Para ejecutar a los clientes, se deben situar en la carpeta y ejecutar cada cliente correspondiente
```bash
cd ./tmp/tp1-g5-client-2024.2Q/

sh administrationClient.sh -DserverAddress=xx.xx.xx.xx:yyyy -Daction=actionName [ -Ddoctor=doctorName | -Dlevel=doctorLevel | -Davailability=availability ]

sh waitingRoomClient.sh -DserverAddress=xx.xx.xx.xx:yyyy -Daction=actionName [ -Dpatient=patientName |  -Dlevel=patientLevel ]

sh emergencyCareClient.sh -DserverAddress=xx.xx.xx.xx:yyyy -Daction=actionName [ -Droom=room -Ddoctor=doctorName -Dpatient=patientName ]

sh queryClient.sh -DserverAddress=xx.xx.xx.xx:yyyy -Daction=actionName [ -DoutPath=outPath -Droom=room ]
````

Las acciones disponibles para administrationClient

| Action      | 
|-------------|
| addDoctor   |
| setDoctor   |
| checkDoctor |
| addRoom     | 

Las acciones disponibles para waitingRoomClient

| Action       | 
|--------------|
| addPatient   |
| updateLevel  |
| checkPatient |
| addRoom      | 

Las acciones disponibles para emergencyCareClient

| Action           | 
|------------------|
| carePatient      |
| careAllPatients  |
| dischargePatient | 

Las acciones disponibles para queryClient

| Action           |
|------------------|
| queryRooms       |
| queryWaitingRoom |
| queryCare        | 
