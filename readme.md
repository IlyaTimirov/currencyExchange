# REST API | ����� ����� 
��� ��������� ����� � ������ [������� �������!](https://zhukovsd.github.io/java-backend-learning-course/ )

![����������� � �������](https://cdn-icons-png.flaticon.com/512/2722/2722070.png)
# �������� 
API ��� �������� ����� � �������� ������. ��������� ������������� � ������������� ������ ����� � �������� ������, � ��������� ������ ����������� ������������ ���� �� ����� ������ � ������.
# ����������
- Java 17
- Java Servlets
- SQLite
- Lombok
- Maven
# ����������
## ������
### GET `/currencies`
��������� ������ �����. ������ ������:
```
[
    {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },   
    {
        "id": 0,
        "name": "Euro",
        "code": "EUR",
        "sign": "�"
    }
]
```
### GET `/currency/EUR`
��������� ���������� ������. ������ ������:
```
{
    "id": 0,
    "name": "Euro",
    "code": "EUR",
    "sign": "�"
}
```

### POST `/currencies`
���������� ����� ������ � ����. ������ ���������� � ���� ������� � ���� ����� ����� (x-www-form-urlencoded).
```
{
    "id": 0,
    "name": "Euro",
    "code": "EUR",
    "sign": "�"
}
```
## �������� �����
### GET `/exchangeRates`
��������� ������ ���� �������� ������.
```
[
    {
        "id": 0,
        "baseCurrency": {
            "id": 0,
            "name": "United States dollar",
            "code": "USD",
            "sign": "$"
        },
        "targetCurrency": {
            "id": 1,
            "name": "Euro",
            "code": "EUR",
            "sign": "�"
        },
        "rate": 0.99
    }
]
```
### GET `/exchangeRate/USDRUB`
��������� ����������� ��������� �����. �������� ���� ������� ������� ������ ������ ����� � ������ �������. ������ ������:

```
{
    "id": 0,
    "baseCurrency": {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },
    "targetCurrency": {
        "id": 1,
        "name": "Euro",
        "code": "EUR",
        "sign": "�"
    },
    "rate": 0.99
}
```

### POST `/exchangeRates`
���������� ������ ��������� ����� � ����.
```
{
    "id": 0,
    "baseCurrency": {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },
    "targetCurrency": {
        "id": 1,
        "name": "Euro",
        "code": "EUR",
        "sign": "�"
    },
    "rate": 0.99
}
```
### PATCH `/exchangeRate/USDRUB`
���������� ������������� � ���� ��������� �����.
```
{
    "id": 0,
    "baseCurrency": {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },
    "targetCurrency": {
        "id": 1,
        "name": "Euro",
        "code": "EUR",
        "sign": "�"
    },
    "rate": 0.99
}
```
## ����� ������
### GET `/exchange?from=BASE_CURRENCY_CODE&to=TARGET_CURRENCY_CODE&amount=$AMOUNT`
������ �������� ������������ ���������� ������� �� ����� ������ � ������. ������ ������� - `GET /exchange?from=USD&to=AUD&amount=10`.
```
{
    "baseCurrency": {
        "id": 0,
        "name": "United States dollar",
        "code": "USD",
        "sign": "$"
    },
    "targetCurrency": {
        "id": 1,
        "name": "Australian dollar",
        "code": "AUD",
        "sign": "A�"
    },
    "rate": 1.45,
    "amount": 10.00,
    "convertedAmount": 14.50
}
```