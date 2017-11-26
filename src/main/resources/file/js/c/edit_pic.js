/**
 * 
 */
var console = window.console || {
	log : function() {
	}
};
var URL = window.URL || window.webkitURL;
var $image = $('#image');
var $download = $('#download');
var $dataX = $('#dataX');
var $dataY = $('#dataY');
var $dataHeight = $('#dataHeight');
var $dataWidth = $('#dataWidth');
var $dataRotate = $('#dataRotate');
var $dataScaleX = $('#dataScaleX');
var $dataScaleY = $('#dataScaleY');
var options = {
	aspectRatio : 1 / 1,
	viewMode : 2,
	toggleDragModeOnDblclick : false,
	dragMode : 'move',
	preview : '.img-preview',
	responsive : true,
	crop : function(e) {
		$dataX.val(Math.round(e.x));
		$dataY.val(Math.round(e.y));
		$dataHeight.val(Math.round(e.height));
		$dataWidth.val(Math.round(e.width));
		$dataRotate.val(e.rotate);
		$dataScaleX.val(e.scaleX);
		$dataScaleY.val(e.scaleY);
	}
};
var originalImageURL = $image.attr('src');
var uploadedImageType = 'image/jpeg';
var uploadedImageURL;

//$('#image').cropper(options);

$image.on(
		{
			ready : function(e) {
				console.log(e.type);
			},
			cropstart : function(e) {
				console.log(e.type, e.action);
			},
			cropmove : function(e) {
				console.log(e.type, e.action);
			},
			cropend : function(e) {
				console.log(e.type, e.action);
			},
			crop : function(e) {
				console.log(e.type, e.x, e.y, e.width, e.height, e.rotate,
						e.scaleX, e.scaleY);
			},
			zoom : function(e) {
				console.log(e.type, e.ratio);
			}
		}).cropper(options);

var $inputImage = $('#inputImage');

if (URL) {
	$inputImage.change(function() {
		var files = this.files;
		var file;

		if (!$image.data('cropper')) {
			return;
		}

		if (files && files.length) {
			file = files[0];

			if (/^image\/\w+$/.test(file.type)) {
				uploadedImageType = file.type;

				if (uploadedImageURL) {
					URL.revokeObjectURL(uploadedImageURL);
				}

				uploadedImageURL = URL.createObjectURL(file);
				$image.cropper('destroy').attr('src', uploadedImageURL)
						.cropper(options);
				$inputImage.val('');
			} else {
				window.alert('Please choose an image file.');
			}
		}
	});
} else {
	$inputImage.prop('disabled', true).parent().addClass('disabled');
}