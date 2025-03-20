from fastapi import FastAPI
from fastapi.responses import FileResponse
from fastapi.staticfiles import StaticFiles
import os

app = FastAPI()

# Serve the entire 'front' folder at '/'
app.mount("/static", StaticFiles(directory=os.path.abspath("../../front")), name="static")

app.mount("/resources", StaticFiles(directory=os.path.abspath("../../front/resources")), name="resources")
app.mount("/src", StaticFiles(directory=os.path.abspath("../../front/src")), name="src")

@app.get("/")
async def serve_index():
    return FileResponse(os.path.abspath("../../front/index.html"))