import boto3
import requests
import os

client = boto3.client('ssm', region_name = 'us-east-1')

env = os.environ.get('APP_ENV')

master_username = client.get_parameter(
    Name=f'{env}_master_username',
    WithDecryption=True
)["Parameter"]["Value"]

db_password = client.get_parameter(
    Name=f'{env}_db_password',
    WithDecryption=True
)["Parameter"]["Value"]

endpoint = client.get_parameter(
    Name=f'{env}_endpoint',
    WithDecryption=True
)["Parameter"]["Value"]

db_instance_name = client.get_parameter(
    Name=f'{env}_db_instance_name',
    WithDecryption=True
)["Parameter"]["Value"]


if __name__ == "__main__":
    print(master_username, db_password, endpoint, db_instance_name)
