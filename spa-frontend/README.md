# Nabd Frontend

This is the frontend application for the Nabd health monitoring system. It provides a user interface for users to monitor their health data, manage their profile, and interact with the wearable device.

## Features

- User authentication (login, register, password reset)
- Health measurements display
- Profile management
- Real-time measurement updates
- Responsive design

## Prerequisites

- Node.js (v16 or higher)
- npm (v7 or higher)

## Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd nabd-system/spa-frontend
```

2. Install dependencies:
```bash
npm install
```

3. Create a `.env` file in the root directory with the following variables:
```
VITE_API_URL=http://localhost:8080
VITE_HEALTH_DATA_URL=http://localhost:8084
```

## Development

To start the development server:

```bash
npm run dev
```

The application will be available at `http://localhost:5173`.

## Building for Production

To build the application for production:

```bash
npm run build
```

The built files will be in the `dist` directory.

## Project Structure

```
spa-frontend/
├── src/
│   ├── assets/          # Static assets
│   ├── components/      # Reusable components
│   ├── router/          # Vue Router configuration
│   ├── utils/           # Utility functions
│   ├── views/           # Page components
│   ├── App.vue          # Root component
│   └── main.js          # Application entry point
├── public/              # Public static files
├── index.html           # HTML template
├── package.json         # Project dependencies
└── vite.config.js       # Vite configuration
```

## API Integration

The frontend integrates with the following backend services:

- User Service (port 8080): Handles user authentication and profile management
- Health Data Service (port 8084): Manages health measurements and device data

## Contributing

1. Create a new branch for your feature
2. Make your changes
3. Submit a pull request

## License

This project is licensed under the MIT License.
