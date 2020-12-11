    // the link to your model provided by Teachable Machine export panel
    const URL = "/my_model/";
    let model, webcam, ctx, labelContainer, maxPredictions, labelVal;

    async function init() {
        const modelURL = URL + "model.json";
        const metadataURL = URL + "metadata.json";

        // load the model and metadata
        // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
        // Note: the pose library adds a tmPose object to your window (window.tmPose)
        model = await tmPose.load(modelURL, metadataURL);
        maxPredictions = model.getTotalClasses();

        // Convenience function to setup a webcam
        const size = 300;
        const flip = true; // whether to flip the webcam
        webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
        await webcam.setup(); // request access to the webcam
        await webcam.play();
        window.requestAnimationFrame(loop);

        // append/get elements to the DOM
        const canvas = document.getElementById("canvas");
        canvas.width = size;
        canvas.height = size;
        ctx = canvas.getContext("2d");
        labelContainer = document.getElementById("label-container");
        labelContainer.appendChild(document.createElement("div"));
//        for (let i = 0; i < maxPredictions; i++) { // and class labels
//            labelContainer.appendChild(document.createElement("div"));
//        }
    }

    async function loop(timestamp) {
        webcam.update(); // update the webcam frame
        await predict();
        window.requestAnimationFrame(loop);
    }

    async function predict() {
        // Prediction #1: run input through posenet
        // estimatePose can take in an image, video or canvas html element
        const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
        // Prediction 2: run input through teachable machine classification model
        const prediction = await model.predict(posenetOutput);

//        console.log(labelContainer.innerHTML);

         let classPrediction = "";
         let highest = 0;

        for (let i = 0; i < maxPredictions; i++) {
            if (prediction[i].probability.toFixed(2) > highest) {
               highest = prediction[i].probability.toFixed(2);
               classPrediction = prediction[i].className;
            }
        }

         labelContainer.innerHTML = classPrediction;
         document.getElementById("labelVal").value = classPrediction;

        // finally draw the poses
        drawPose(pose);
    }






//// run the webcam image through the image model
//    async function predict() {
//      // predict can take in an image, video or canvas html element
//		const prediction = await model.predict(webcam.canvas);
//
//		let highestProb = 0;
//		let chocName = "";
//
//		prediction.forEach(function(pred){
//			if (pred.probability > highestProb) {
//				highestProb = pred.probability;
//				chocName = pred.className;
//				console.log(pred.probability);
//			}
//		});
//
////     const delayedStoreChocolate = _.debounce(function(){
////         storeChocolate(chocName);
////     }, 5000);
////
////		if (chocName !== "Nothing"){
////         throttledStoreChocolate(chocName);
////		}
//
//      drawPose(pose);
//
//    }

    function drawPose(pose) {
        if (webcam.canvas) {
            ctx.drawImage(webcam.canvas, 0, 0);
            // draw the keypoints and skeleton
            if (pose) {
                const minPartConfidence = 0.5;
                tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx);
                tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx);
            }
        }
    }

    function test() {
      alert("This is looking good...");
    }
