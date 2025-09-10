#!/bin/sh
set -e

# Variables configurables via environnement
host="${DB_HOST:-db}"
port="${DB_PORT:-5432}"
user="${DB_USER:-postgres}"
max_retries="${MAX_RETRIES:-30}"

echo "⏳ Waiting for database at $host:$port..."

count=0
until PGPASSWORD=$SPRING_DATASOURCE_PASSWORD pg_isready -h "$host" -p "$port" -U "$user" > /dev/null 2>&1; do
  count=$((count + 1))
  if [ "$count" -ge "$max_retries" ]; then
    echo "❌ Database did not become ready in time"
    exit 1
  fi
  echo "Database not ready, retrying..."
  sleep 2
done

echo "✅ Database is up, starting app..."
exec java -jar app.jar
