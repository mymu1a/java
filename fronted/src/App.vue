<template>
  <div id="app">
    <nav>
      <router-link to="/time-converter" class="nav-button">Time Converter</router-link> |
      <router-link to="/time-device-data" class="nav-button">Time Device Data</router-link>
    </nav>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
/* Global Styles */
body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  background-color: #0d1117; /* Dark background */
  color: #c9d1d9; /* Muted text color */
  margin: 0;
  padding: 0;
}

/* Additional Global Styles */
h1 {
  color: #c9d1d9; /* Muted header color */
}

form {
  background-color: #161b22; /* Dark form background */
  border: 1px solid #30363d; /* Muted border color */
  border-radius: 6px;
  padding: 16px;
  margin-bottom: 24px;
}

input {
  background-color: #0d1117; /* Dark input background */
  border: 1px solid #30363d; /* Muted border color */
  border-radius: 6px;
  padding: 8px 12px;
  font-size: 16px;
  color: #c9d1d9; /* Muted input text color */
  margin-right: 8px;
}

button {
  background-color: #238636; /* Green button background */
  border: 1px solid rgba(27, 31, 35, 0.15);
  border-radius: 6px;
  color: #ffffff; /* White button text */
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  padding: 8px 16px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  background-color: #161b22; /* Dark list item background */
  border: 1px solid #30363d; /* Muted border color */
  border-radius: 6px;
  padding: 16px;
  margin-bottom: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

li button {
  margin-left: 16px;
}

@media (max-width: 768px) {
  form {
    padding: 12px;
  }

  input {
    font-size: 14px;
    padding: 6px 10px;
    margin-right: 6px;
  }

  button {
    font-size: 14px;
    padding: 6px 12px;
  }

  li {
    padding: 12px;
  }
}

/* Styles for router-link as buttons */
.nav-button {
  background-color: #238636; /* Green button background */
  border: 1px solid rgba(27, 31, 35, 0.15);
  border-radius: 6px;
  color: #ffffff; /* White button text */
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  padding: 8px 16px;
  text-decoration: none;
  display: inline-block;
  margin-right: 8px;
}

.nav-button:hover {
  background-color: #2ea043; /* Darker green on hover */
}

.nav-button-exact-active {
  background-color: #2ea043; /* Darker green for active link */
}
</style>