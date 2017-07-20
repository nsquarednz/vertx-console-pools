<template>
    <div class="usage-map">
        <div class="square" :class="usedSquareClass" v-for="n in used" :key="n" /><div class="square avail" v-for="n in avail" :key="n" /><div class="square empty" v-for="n in empty" :key="n" />
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
        },
        usedSquareClass() {
            const ratio = this.used / this.max;
            if (ratio > 0.6) {
                return 'used-hot';
            } else if (ratio > 0.33) {
                return 'used-warm';
            } else {
                return 'used';
            }
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

.used-warm {
    background: #f39c3d;
}

.used-hot {
    background: #cd0000;
}

.avail {
    background: #bee1f4;
}

.empty {
    background: #f1f1f1;
}
</style>