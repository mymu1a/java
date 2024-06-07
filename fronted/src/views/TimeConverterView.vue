<template>
  <div>
    <TimeConverterForm />
  </div>
</template>

<script>
import TimeConverterForm from '@/components/TimeConverterForm.vue';

export default {
  name: 'TimeConverterView',
  components: {
    TimeConverterForm
  }
};
</script>

<style scoped>
div {
  padding: 20px;
}
</style>
