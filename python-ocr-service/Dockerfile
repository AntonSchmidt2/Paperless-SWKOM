# Use an official Python runtime as a parent image
FROM python:3.9-slim


# Create the working directory in the container
RUN mkdir /app

# Set the working directory in the container
WORKDIR /app

# Install system dependencies for Poppler
RUN apt-get update \
    && apt-get install -y \
    tesseract-ocr \
    poppler-utils \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Copy the current directory contents into the container at /app
COPY . /app

# Install any needed packages specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Define environment variable
ENV NAME ocr_worker

# Run ocr_worker.py when the container launches
CMD ["python", "./app/ocr_worker.py"]
