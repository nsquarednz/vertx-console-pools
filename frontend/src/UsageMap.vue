<template>
  <div class="usage-map">{{ used }} + {{ avail }} ({{ empty }})</div>
</template>

<script>
export default {
    props: {
        used: Number,
        max: Number,
        largest: Number
    },
    computed: {
        avail() {
            return this.max - this.used;
        },
        empty() {
            return this.largest - this.max;
        }
    }
}
</script>

<style lang="scss" scoped>
.usage-map {
    width: 100%;
}
</style>