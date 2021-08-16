from __future__ import print_function
from datetime import date, datetime, timedelta
import mysql.connector
import json
cnx = mysql.connector.connect(user='natalia2', password="Bucharest2021", database='bucharest')
cursor = cnx.cursor()

index = 1

responses = [
    "response-9-10.json",
    "response-8-9.json",
    "response-7-8.json",
    "response-6-7.json",
    "response-6-6.json",
    "response-5-6.json",
    "response-5-5.json",
    "response-5-5-2.json",
    "response-5-5-3.json",
    "response-5-5-4.json",
    "response-5-5-5.json",
    "response-5-5-6.json",
    "response-5-5-7.json",
    "response-5-5-8.json",
    "response-5-5-9.json",
    "response-5-5-10.json"
]

def get_address(lat, lon):
    import http.client, urllib.parse

    conn = http.client.HTTPConnection('api.positionstack.com')

    lat_lon = str(lat) + "," + str(lon)

    params = urllib.parse.urlencode({
        'access_key': 'fc8c9f937373807ba8e4067bc2ba86c3',
        'query': lat_lon,
        })

    conn.request('GET', '/v1/reverse?{}'.format(params))

    res = conn.getresponse()
    data = res.read()
    data = json.loads(data)
#     print(data)

    if "data" not in data:
        return "Bucharest"

    street = data["data"][0]["street"]
    number = data["data"][0]["number"]

    if street != None:
        if number == None:
            address = street
        else:
            address = street + ", nr. " + number

        return address
    else:
        return data["data"][0]["region"]

for response in responses:
    with open(response) as f:
        data = json.load(f)
        print(len(data["results"]))
        for i in range(0, len(data["results"])):
            name = data["results"][i]["name"]
            description = data["results"][i]["snippet"]
            address = data["results"][i]["location_id"]
            score = data["results"][i]["score"]
            if len(data["results"][i]["images"]) > 0:
                image = data["results"][i]["images"][0]["sizes"]["original"]["url"]
            else:
                image = ""

            print(data["results"][i]["name"])
            print(data["results"][i]["snippet"])
            print("***")

            lat = data["results"][i]["coordinates"]["latitude"]
            lon = data["results"][i]["coordinates"]["longitude"]

            add_attraction = ("INSERT INTO attraction "
                           "(id, address, description, name, image, score) "
                           "VALUES (%s, %s, %s, %s, %s, %s)")
            data_attraction = (index, get_address(lat, lon), description, name, image, score)
            # Insert new employee
            cursor.execute(add_attraction, data_attraction)
            # Make sure data is committed to the database

            index += 1
            cnx.commit()


cursor.close()
cnx.close()