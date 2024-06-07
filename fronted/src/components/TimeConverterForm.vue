<template>
  <div>
    <h1>Time Converter</h1>
    <form @submit.prevent="convertAndAddTime">
      <input v-model="milliseconds" placeholder="Milliseconds" required />
      <button type="submit">Convert and Add</button>
    </form>
    <ul>
      <li v-for="converter in converters" :key="converter.id">
        ID: {{ converter.id }}, Milliseconds: {{ converter.milliseconds }}, Converted Time: {{ converter.convertedTime }}
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
      milliseconds: '',
      converters: []
    };
  },
  methods: {
    async fetchConverters() {
      try {
        const response = await axios.get('http://localhost:8080/time-converter/');
        this.converters = response.data;
      } catch (error) {
        console.error('Failed to fetch converters', error);
      }
    },
    async convertAndAddTime() {
      try {
        // Call the convert endpoint first
        const convertResponse = await axios.get(`http://localhost:8080/date-convert`, {
          params: { milliseconds: this.milliseconds }
        });
        const convertedTime = convertResponse.data.convertedTime;

        // Then save the converter entry
        const response = await axios.post('http://localhost:8080/time-converter/', {
          milliseconds: this.milliseconds,
          convertedTime: convertedTime
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
    }
  },
  mounted() {
    this.fetchConverters();
  }
};
</script>

<style scoped>
h1 {
  color: blue;
}
form {
  margin-bottom: 20px;
}
input {
  margin-right: 10px;
}
button {
  margin-left: 5px;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 10px 0;
}
</style>
