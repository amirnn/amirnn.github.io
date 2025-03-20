#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

echo "🚀 Starting Python environment setup..."

# Create Python virtual environment if it doesn't exist
if [ ! -d "venv" ]; then
    echo "✅ Creating Python virtual environment..."
    python -m venv venv
fi

# Activate the virtual environment
source ./venv/bin/activate

# Install Python package dependencies
echo "📦 Installing Python packages..."
pip install --upgrade pip
pip install fastapi uvicorn gunicorn
echo "✅ Python environment setup complete!"
