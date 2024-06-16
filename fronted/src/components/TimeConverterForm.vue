<template>
  <div class="container">
    <h1>Time Converter</h1>
    <form @submit.prevent="convertAndAddTime">
      <input v-model="milliseconds" placeholder="Milliseconds" required />
      <button type="submit">Add</button>
    </form>
    <ul>
      <li v-for="converter in timeConverters" :key="converter.id" class="converter-item">
        <div class="converter-info">
          <div>ID: {{ converter.id }}</div>
          <div>Local Time: {{ formatDate(converter.localTime) }}</div>
          <div>GMT Time: {{ formatDate(converter.gmtTime) }}</div>
        </div>
        <div class="device-data-list">
          <div>Time Device Data List:</div>
          <ul>
            <li v-for="deviceData in converter.timeDeviceDataList" :key="deviceData.id" class="device-data-item">
              Device ID: {{ deviceData.id }}, Device Time: {{ formatDate(deviceData.deviceTime) }}
            </li>
          </ul>
        </div>
        <button @click="deleteConverter(converter.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      timeConverters: [],
      milliseconds: '',
    };
  },
  methods: {
    async fetchConverters() {
      try {
        const response = await axios.get('http://localhost:8080/time-converter/');
        this.timeConverters = response.data;
      } catch (error) {
        console.error('Failed to fetch converters', error);
      }
    },
    async convertAndAddTime() {
      try {
        await axios.get('http://localhost:8080/date-convert', {
          params: { milliseconds: this.milliseconds },
        });
        this.milliseconds = '';
        this.fetchConverters();
      } catch (error) {
        console.error('Failed to add converter', error);
      }
    },
    async deleteConverter(id) {
      try {
        await axios.delete(`http://localhost:8080/time-converter/${id}`);
        this.fetchConverters();
      } catch (error) {
        console.error('Failed to delete converter', error);
      }
    },
    formatDate(date) {
      const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit' };
      return new Date(date).toLocaleDateString(undefined, options);
    }
  },
  mounted() {
    this.fetchConverters();
  },
};
</script>

<style scoped>
body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  background-color: #24292e;
  color: #f0f6fc;
  margin: 0;
  padding: 0;
}

.container {
  max-width: 800px; /* Set the maximum width */
  margin: 0 auto; /* Center the container */
  padding: 16px;
}

h1 {
  color: #f0f6fc;
  font-size: 32px;
  font-weight: 600;
  margin-bottom: 16px;
}

form {
  background-color: #1f1f1f; /* Darker form background */
  border: 1px solid #e1e4e8;
  border-radius: 6px;
  padding: 16px;
  margin-bottom: 24px;
}

input {
  border: 1px solid #e1e4e8;
  background-color: #2d333b; /* Darker input fields */
  border-radius: 6px;
  padding: 8px 12px;
  font-size: 16px;
  color: #f0f6fc;
  margin-right: 8px;
}

button {
  background-color: #2ea44f;
  border: 1px solid rgba(27, 31, 35, 0.15);
  border-radius: 6px;
  color: #ffffff;
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
  background-color: #1f1f1f; /* Darker list item background */
  border: 1px solid #e1e4e8;
  border-radius: 6px;
  padding: 16px;
  margin-bottom: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.converter-item {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
}

.converter-info {
  flex: 1;
}

.device-data-list {
  flex: 2; /* Increased flex value to make this section larger */
  margin-left: 16px;
}

.device-data-item {
  white-space: nowrap; /* Prevent text from wrapping */
}

li button {
  margin-left: 16px;
  background-color: #d73a49;
  border: 1px solid rgba(27, 31, 35, 0.15);
  border-radius: 6px;
  color: #ffffff;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  padding: 8px 16px;
}
</style>
