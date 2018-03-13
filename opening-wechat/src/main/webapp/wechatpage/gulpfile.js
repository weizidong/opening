var gulp = require('gulp');
var uglify = require("gulp-uglify");
var minifyCss = require("gulp-minify-css");
var minifyHtml = require("gulp-minify-html");
var del = require('del');
var imagemin = require('gulp-imagemin');

gulp.task('clean', function (cb) {
    return del(['dist/**/*'], cb);
});

gulp.task('build', ['clean'], function () {
    gulp.src('*.html').pipe(minifyHtml()).pipe(gulp.dest('dist'))
    gulp.src('css/*.css').pipe(minifyCss()).pipe(gulp.dest('dist/css'))
    gulp.src('js/*.js').pipe(uglify()).pipe(gulp.dest('dist/js'))
    gulp.src('img/*.{png,jpg,gif,ico}').pipe(imagemin()).pipe(gulp.dest('dist/img'));
    gulp.src('fonts/*').pipe(gulp.dest('dist/fonts'));
})