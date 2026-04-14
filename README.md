🌐 [Português (BR)](README.pt_BR.md) | [Español](README.es.md)

# Soc Ops

A social bingo game for in‑person mixers. Hand everyone a board, start meeting people, and race to get 5 in a row.

- Perfect for: offsites, meetups, hackathons, classrooms
- No logins or accounts – just a browser
- Built as a reference app for modern Java + Spring Boot 3

📚 **Running this as a workshop? Start with the [Lab Guide](workshop/GUIDE.md).**

---

## 🎮 What you get

- **Icebreaker bingo boards** generated on the fly
- **Simple, single-page UI** served by Spring Boot
- **Local persistence** so players can refresh without losing progress
- **Clean Java 21 codebase** designed for teaching, extending, and experimenting

---

## 🚀 Quickstart

1. **Install prerequisites**
   - [Java 21 JDK](https://adoptium.net/)
   - [Apache Maven 3.9+](https://maven.apache.org/) (or use the included Maven Wrapper)
2. **Run the dev server**
   ```bash
   cd socops
   ./mvnw spring-boot:run
   ```
3. **Open the game**
   - Visit http://localhost:8080 in your browser
   - Hit “New Board” and start playing

---

## 🛠 Development

### Build
```bash
cd socops
./mvnw clean package
```

### Test
```bash
cd socops
./mvnw test
```

---

## 🧪 Workshop & Lab Guide

This repository also powers a hands-on lab exploring Copilot and multi-agent development.

| Part | Title |
|------|-------|
| [**00**](workshop/00-overview.md) | Overview & Checklist |
| [**01**](workshop/01-setup.md) | Setup & Context Engineering |
| [**02**](workshop/02-design.md) | Design-First Frontend |
| [**03**](workshop/03-quiz-master.md) | Custom Quiz Master |
| [**04**](workshop/04-multi-agent.md) | Multi-Agent Development |

> 📝 Lab guides are also available in the [`workshop/`](workshop/) folder for offline reading.

---

## 📦 CI & Deployment

This project is set up to deploy automatically (e.g., to GitHub Pages or your chosen target) on push to the `main` branch. Check your repository settings or workflows to customize the deployment.
