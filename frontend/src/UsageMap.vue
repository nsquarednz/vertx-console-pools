<template>
    <div class="usage-map">
        <div class="square used" v-for="n in used" :key="n" /><div class="square avail" v-for="n in avail" :key="n" /><div class="square empty" v-for="n in empty" :key="n" />
    </div>
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
    margin: 10px 0;
    line-height: 12px;
}

.square {
    width: 20px;
    height: 20px;
    display: inline-block;
    margin-right: 2px;
}

.used {
    background: #f9d67a;
}

.avail {
    background: #bee1f4;
}

.empty {
    background: #e1e1e1;
}
</style>