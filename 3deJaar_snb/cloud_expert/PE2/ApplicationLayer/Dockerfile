# Use an official Python runtime as a parent image
FROM python:3.9-slim

# Set the working directory in the container
WORKDIR /app

# Copy the requirements file first to leverage Docker's caching
COPY parameters.py .
COPY app.py .
COPY requirements.txt .


# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt
RUN pip install ddtrace

# Copy the rest of the application code into the container

# Expose the port that the application runs on
EXPOSE 4000

# Set the default command to run the application
CMD ["ddtrace-run", "python", "app.py"]