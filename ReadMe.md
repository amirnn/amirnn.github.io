
# FastAPI Backend with Static Frontend

## Project Overview
This repository contains a **FastAPI backend** that serves a **static frontend** and provides the foundation for deploying the application on a **Google Cloud VM** using **Gunicorn** and **NGINX** for production.

### 📁 Project Structure
```
back/python/serve.py         # FastAPI application
back/python/venv/            # Python virtual environment
front/                       # Frontend (HTML, CSS, JS)
```

## 🏗 Features
✅ FastAPI backend  
✅ Serves `index.html` and static files (`/static`, `/resources`, `/src`)  
✅ Production-ready deployment with Gunicorn and NGINX  
✅ Systemd service for automatic startup on boot  
✅ Ready for HTTPS with Let's Encrypt (SSL encryption)  

## 🚀 How to Run Locally (Development)
### 1️⃣ Create and activate a virtual environment:
```bash
cd back/python
python3 -m venv venv
source venv/bin/activate
pip install fastapi uvicorn
```

### 2️⃣ Run the app:
```bash
uvicorn serve:app --reload --host 0.0.0.0 --port 8000
```

### 3️⃣ Access the frontend:
```
http://localhost:8000/
```

## ☁️ Deployment on Google Cloud VM
### 1️⃣ Install system dependencies:
```bash
sudo apt update
sudo apt install python3 python3-venv python3-pip nginx certbot python3-certbot-nginx
```

### 2️⃣ Set up virtual environment & dependencies:
```bash
cd /path/to/back/python
python3 -m venv venv
source venv/bin/activate
pip install fastapi gunicorn uvicorn
```

### 3️⃣ Create a systemd service:
`/etc/systemd/system/fastapi.service`
```ini
[Unit]
Description=FastAPI Gunicorn Service
After=network.target

[Service]
User=fastapiuser
WorkingDirectory=/path/to/back/python
ExecStart=/path/to/back/python/venv/bin/gunicorn -w 4 -k uvicorn.workers.UvicornWorker serve:app --bind 127.0.0.1:8000
Restart=always

[Install]
WantedBy=multi-user.target
```

### 4️⃣ Reload and start the service:
```bash
sudo systemctl daemon-reload
sudo systemctl start fastapi.service
sudo systemctl enable fastapi.service
```

## 🌐 NGINX Reverse Proxy Example
```nginx
server {
    listen 80;
    server_name your-domain.com;

    location / {
        proxy_pass http://127.0.0.1:8000;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

Reload NGINX:
```bash
sudo systemctl reload nginx
```

## 🔒 Enable SSL with Let's Encrypt
### 1️⃣ Point your domain to your Google Cloud VM's external IP
### 2️⃣ Obtain and install the SSL certificate:
```bash
sudo certbot --nginx -d your-domain.com
```
- Certbot will automatically configure SSL in NGINX
- You can test auto-renewal with:
```bash
sudo certbot renew --dry-run
```

### ✅ After this, your site will be accessible via:
```
https://your-domain.com
```

## 🔐 Security Recommendations
✅ Use `fastapiuser` with limited permissions  
✅ Protect port `8000` with GCP firewall rules  
✅ Enable HTTPS with Let's Encrypt  
✅ Validate API inputs with Pydantic  
✅ Keep system and Python dependencies updated  

## 🛠 Future Improvements
- Add more API endpoints
- Add frontend framework support (React/Vue)

## 📄 Usage Rights
The code provided here is shared in the spirit of free education. Please do not use this code directly for your coursework or professional projects. Instead, let these implementations serve as inspiration to develop your own solutions.

If you have any questions, suggestions, or feedback, feel free to contact me.

© 2025 by Amir Nourinia