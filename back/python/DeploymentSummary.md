
# ✅ Deployment Summary for FastAPI + Static Frontend on Google Cloud VM

## 1️⃣ Google Cloud VM Setup
- Create a new Ubuntu VM instance on GCP
- Open ports `22`, `80`, and `443` in the GCP Firewall (VPC)

## 2️⃣ System Dependencies Installation
```bash
sudo apt update
sudo apt install -y python3 python3-venv python3-pip nginx certbot python3-certbot-nginx
```

## 3️⃣ Create a Dedicated FastAPI User (Non-login)
```bash
sudo useradd -r -s /bin/false fastapiuser
```

## 4️⃣ Python Environment Setup
- Navigate to the project directory:
```bash
cd /home/amirnourinia/Developer/amirnn.github.io/back/python
```
- Run the provided setup script:
```bash
chmod +x setup_python_env.sh
./setup_python_env.sh
```

## 5️⃣ Create systemd Service for FastAPI + Gunicorn
- Create `/etc/systemd/system/fastapi.service`:
```ini
[Unit]
Description=FastAPI Gunicorn Service
After=network.target

[Service]
User=fastapiuser
WorkingDirectory=/home/amirnourinia/Developer/amirnn.github.io/back/python
ExecStart=/home/amirnourinia/Developer/amirnn.github.io/back/python/venv/bin/gunicorn -w 4 -k uvicorn.workers.UvicornWorker serve:app --bind 127.0.0.1:8000
Restart=always

[Install]
WantedBy=multi-user.target
```
- Start and enable the service:
```bash
sudo systemctl daemon-reload
sudo systemctl start fastapi.service
sudo systemctl enable fastapi.service
```

## 6️⃣ NGINX Reverse Proxy Setup
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
```bash
sudo systemctl reload nginx
```

## 7️⃣ Enable SSL (HTTPS) with Let's Encrypt
```bash
sudo certbot --nginx -d your-domain.com
```
- Test auto-renewal:
```bash
sudo certbot renew --dry-run
```

## 8️⃣ Security Best Practices
✅ Run service as `fastapiuser` with limited permissions  
✅ Use GCP firewall to block public access to port `8000`  
✅ Validate inputs with FastAPI + Pydantic  
✅ Use UFW for extra protection (optional)

## 9️⃣ Access the Application
- HTTP: `http://your-domain.com/`
- HTTPS (after SSL): `https://your-domain.com/`

---

✅ **Deployment Complete!** Your FastAPI app is now production-ready on Google Cloud VM.
