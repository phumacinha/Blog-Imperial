<template>
  <div class="label">
    <label :for="name"> {{label}} </label> <br>
    <input v-if="type === 'input'" type="text" :name="name" :id="name" :value="value" autocomplete="off" required>
    <textarea v-if="type === 'textarea'" :name="name" :id="name" :value="value"></textarea>
  </div>
</template>

<script>
export default {
  props: {
    type: {
      type: String,
      default: "input"
    },
    label: String,
    name: String,
    value: {
      type: String,
      default: ""
    }

  }
}
</script>

<style scoped>

</style>