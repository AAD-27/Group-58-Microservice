FROM node:20.10.0-alpine

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install --production 

# Copy application source code
COPY . .

# Copy the wait-for-mysql script and make it executable
COPY wait-for-mysql.sh /wait-for-mysql.sh
RUN chmod +x /wait-for-mysql.sh

# Build the NestJS application
RUN npm run build

# Expose port the app listens on
EXPOSE 3000

# Set environment variables for DB - override at runtime as needed
ENV DB_HOST=mysql
ENV DB_USER=root
ENV DB_PASS=root
ENV DB_NAME=local_db

# Use the wait script as the entrypoint to wait for MySQL before starting the app
CMD ["/wait-for-mysql.sh", "mysql", "node", "dist/main.js"]
