# PRACTICA  de preevaluación crediticia

## API
POST http://localhost:8083/api/v1/creditos/evaluacion, Content-Type: application/json.
Importar Confitech.postman_collection.json en Postman. Ejemplo:

```json
{
  "dni": "00000004",
  "nombre": "Stefany",
  "edad": 26,
  "ingreso_neto": 5000,
  "monto_solicitado": 25000,
  "plazo_meses": 24,
  "score_crediticio": 750,
  "clasificacion_sbs": "NORMAL",
  "deuda_morosa_actual": 0
}
```
## Capas
Controller recibe el JSON; DTO valida y representa entrada/salida; Service concentra reglas; Model contiene los objetos del dominio; Exception gestiona errores HTTP. No se requiere Repository ni Entity JPA porque no se solicita guardar datos. El servicio se registra mediante @Bean e inyección por constructor.
